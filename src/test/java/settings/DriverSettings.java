package settings;

import com.thoughtworks.gauge.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.ImmutableMap;

public class DriverSettings {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = Logger.getLogger(DriverSettings.class);

    // Etiketleri almak için statik olmayan metot
    public List<String> getTags(ExecutionContext context) {
        return new ArrayList<>(context.getCurrentScenario().getTags());
    }

    @BeforeSuite
    public static void initWebDriver() {
        try {
            WebDriverManager.chromedriver().setup();
            // WebDriver başlatma işlemi burada olacak (örneğin chrome)
        } catch (Exception e) {
            handleError("WebDriver başlatılırken bir hata oluştu", e);
        }
    }

    @BeforeScenario
    public static void setupScenario(ExecutionContext context) {
        if (driver.get() == null) {
            // WebDriver başlatılmamışsa başlatıyoruz
            List<String> tags = new DriverSettings().getTags(context);

            if (tags.isEmpty()) {
                logger.error("Tag bulunamadı, lütfen geçerli bir tag girin.");
                throw new IllegalStateException("Geçerli bir tag girilmedi");
            }

            // Etiketlere göre uygun driver'ı başlatıyoruz
            if (tags.contains("mobile")) {
                setMobileDriver(tags); // "mobile" tag'i varsa mobil emülasyonu başlat
            } else if (tags.contains("chrome")) {
                setChromeDriver();
            } else if (tags.contains("firefox")) {
                setFirefoxDriver();
            } else if (tags.contains("edge")) {
                setEdgeDriver();
            } else {
                String msg = "Driver için desteklenen tag girilmedi. Desteklenen tagler => chrome, firefox, edge, mobile.";
                logger.error(msg);
                throw new IllegalStateException(msg);
            }
        }
    }

    private static void setMobileDriver(List<String> tags) {
        // Mobile tag'i içinde "android" veya "ios" varsa, o platformu emüle et
        if (tags.contains("android")) {
            setAndroidDriver();
        } else if (tags.contains("ios")) {
            setIOSDriver();
        } else {
            logger.error("Geçersiz mobil platform tag'i. Lütfen 'android' veya 'ios' tag'lerini kullanın.");
            throw new IllegalStateException("Geçersiz mobil platform tag'i.");
        }
    }

    private static void setAndroidDriver() {
        ChromeOptions options = new ChromeOptions();

        // Android için mobile emülasyonu ayarları
        options.setExperimentalOption("mobileEmulation", ImmutableMap.of("deviceName", "Pixel 7"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("browserName", "Chrome");

        WebDriverManager.chromedriver().setup();
        WebDriver yeniDriver = new ChromeDriver(options);
        yeniDriver.manage().window().maximize();
        driver.set(yeniDriver);
        logger.info("Android Mobile Chrome WebDriver başarıyla başlatıldı.");
    }

    private static void setIOSDriver() {
        ChromeOptions options = new ChromeOptions();

        // iOS için mobile emülasyonu ayarları
        options.setExperimentalOption("mobileEmulation", ImmutableMap.of("deviceName", "iPhone 6"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("browserName", "Safari");

        WebDriverManager.chromedriver().setup();
        WebDriver yeniDriver = new ChromeDriver(options);
        yeniDriver.manage().window().maximize();
        driver.set(yeniDriver);
        logger.info("iOS Mobile Safari WebDriver başarıyla başlatıldı.");
    }

    private static void setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup(); // WebDriverManager ile ChromeDriver'ı yükle
        WebDriver yeniDriver = new ChromeDriver(options);
        yeniDriver.manage().window().maximize();
        driver.set(yeniDriver);
        logger.info("Chrome WebDriver başarıyla başlatıldı.");
    }

    private static void setFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup(); // WebDriverManager ile Firefox driver'ını yükle
        WebDriver yeniDriver = new FirefoxDriver(options);
        yeniDriver.manage().window().maximize();
        driver.set(yeniDriver);
        logger.info("Firefox WebDriver başarıyla başlatıldı.");
    }

    private static void setEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        WebDriverManager.edgedriver().setup(); // WebDriverManager ile Edge driver'ını yükle
        WebDriver yeniDriver = new EdgeDriver(options);
        yeniDriver.manage().window().maximize();
        driver.set(yeniDriver);
        logger.info("Edge WebDriver başarıyla başlatıldı.");
    }

    @AfterScenario
    public static void tearDownScenario() {
        WebDriver mevcutDriver = driver.get();
        if (mevcutDriver != null) {
            try {
                mevcutDriver.quit();
                driver.remove();
                logger.info("Senaryo sonrası WebDriver kapatıldı.");
            } catch (Exception e) {
                handleError("Senaryo sonrası WebDriver kapatılırken bir hata oluştu", e);
            }
        }
    }

    @AfterSuite
    public static void quitWebDriver() {
        WebDriver mevcutDriver = driver.get();
        if (mevcutDriver != null) {
            try {
              //  mevcutDriver.quit();
               // driver.remove();
                logger.info("Suite sonrası WebDriver kapatıldı.");
            } catch (Exception e) {
                handleError("Suite sonrası WebDriver kapatılırken bir hata oluştu", e);
            }
        }
    }

    public static WebDriver getWebDriver() {
        WebDriver mevcutDriver = driver.get();
        if (mevcutDriver == null) {
            throw new IllegalStateException("WebDriver başlatılmadı. Lütfen önce WebDriver'ı başlatın.");
        }
        return mevcutDriver;
    }

    private static void handleError(String mesaj, Exception e) {
        logger.error(mesaj + ": " + e.getMessage(), e);
        e.printStackTrace();
        throw new IllegalStateException(mesaj, e);
    }
}
