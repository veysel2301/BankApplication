package definitions;

import com.thoughtworks.gauge.Step;
import org.apache.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.DriverSettings;
import utils.SelectorHelper;
import org.apache.log4j.Logger;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SelectorHelper.*;

public class StepDefinitions {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

    @Step("Web sayfası <url> adresinden açılır.")
    public void openWebPage(String url) {
        driver = DriverSettings.getWebDriver();

        if (driver != null) {
            try {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                driver.get(url);
                // Loglama işlemi
                logger.info("Web sayfası başarıyla açıldı: " + url);
            } catch (Exception e) {
                logger.error("Web sayfası açılamadı: " + e.getMessage(), e);
                throw new IllegalStateException("Web sayfası açılamadı: " + e.getMessage(), e);
            }
        } else {
            logger.error("WebDriver başlatılamadı.");
            throw new IllegalStateException("WebDriver başlatılamadı.");
        }
    }

    @Step("Sayfa başlığının <expectedTitle> olduğu doğrulanır.")
    public void verifyPageTitle(String expectedTitle) {
        try {
            String actualTitle = driver.getTitle();
            assertThat(actualTitle).isEqualTo(expectedTitle);
            logger.info("Sayfa başlığı doğrulandı: " + expectedTitle);
        } catch (Exception e) {
            logger.error("Sayfa başlığı doğrulanamadı: " + e.getMessage(), e);
            throw new IllegalStateException("Sayfa başlığı doğrulanamadı: " + e.getMessage(), e);
        }
    }

    @Step("Sayfada <elementName> elementine tıklanır.")
    public void clickElement(String elementName) {
        try {
            By selector = getBy(elementName);
            if (selector == null) {
                throw new IllegalStateException("Selector bulunamadı: " + elementName);
            }
            WebElement element = driver.findElement(selector);
            element.click();
            assertThat(element.isEnabled()).isTrue();
            logger.info(elementName + " elementine tıklanıldı.");
        } catch (Exception e) {
            logger.error(elementName + " elementine tıklanırken hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException(elementName + " elementine tıklanırken hata oluştu: " + e.getMessage(), e);
        }
    }

    @Step("Sayfada <elementName> alanına <text> yazılır.")
    public void sendKeysToElement(String elementName, String text) {
        try {
            By selector = getBy(elementName);
            if (selector == null) {
                throw new IllegalStateException("Element bulunamadı: " + elementName);
            }
            WebElement element = driver.findElement(selector);
            element.sendKeys(text);
            assertThat(element.getAttribute("value")).isEqualTo(text);
            logger.info(elementName + " alanına metin yazıldı: " + text);
        } catch (Exception e) {
            logger.error(elementName + " alanına yazı yazılırken hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException(elementName + " alanına yazı yazılırken hata oluştu: " + e.getMessage(), e);
        }
    } @Step("Sayfada <elementName> alanı temizlenir ve <text> text degeri yazilir.")
    public void clearElementSendkeys(String elementName, String text) {
        try {
            By selector = getBy(elementName);
            if (selector == null) {
                throw new IllegalStateException("Element bulunamadı: " + elementName);
            }
            WebElement element = driver.findElement(selector);
            element.clear();
            logger.info(elementName + " alanı temizlendi.");

            element.sendKeys(text);
            assertThat(element.getAttribute("value")).isEqualTo(text);
            logger.info(elementName + " alanına metin yazıldı: " + text);
        } catch (Exception e) {
            logger.error(elementName + " alanına yazı yazılırken hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException(elementName + " alanına yazı yazılırken hata oluştu: " + e.getMessage(), e);
        }
    }

    @Step("Sayfada <elementName> elementinin text'i <expectedText> ile doğrulanır.")
    public void verifyElementText(String elementName, String expectedText) {
        try {
            By selector = getBy(elementName);
            if (selector == null) {
                throw new IllegalStateException("Selector bulunamadı: " + elementName);
            }
            WebElement element = driver.findElement(selector);
            String actualText = element.getText();
            assertThat(actualText).isEqualTo(expectedText);
            logger.info(elementName + " elementinin text'i doğrulandı: " + expectedText);
        } catch (Exception e) {
            logger.error("Element metni doğrulanırken hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException("Element metni doğrulanırken hata oluştu: " + e.getMessage(), e);
        }
    }

    @Step("Sayfada <elementName> elementinin varlığı doğrulanır.")
    public void verifyElementPresence(String elementName) {
        try {
            By selector = getBy(elementName);

            if (selector == null) {
                throw new IllegalStateException("Selector bulunamadı: " + elementName);
            }
            WebElement element = driver.findElement(selector);
            assertThat(element.isDisplayed()).isTrue();
            logger.info(elementName + " elementinin varlığı doğrulandı.");
        } catch (Exception e) {
            logger.error(elementName + " elementinin varlığı doğrulanırken hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException(elementName + " elementinin varlığı doğrulanırken hata oluştu: " + e.getMessage(), e);
        }
    }

    @Step("Sayfa, <elementName> elementine kadar kaydırılır.")
    public void scrollToElement(String elementName) {
        try {
            By selector = getBy(elementName);

            if (selector == null) {
                throw new RuntimeException("Selector '" + elementName + "' bulunamadı.");
            }
            WebElement element = driver.findElement(selector);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            assertThat(element.isDisplayed()).isTrue();
            logger.info(elementName + " elementine başarıyla kaydırıldı.");
        } catch (Exception e) {
            logger.error(elementName + " elementine kaydırılırken hata oluştu: " + e.getMessage(), e);
            throw new RuntimeException(elementName + " elementine kaydırılırken hata oluştu: " + e.getMessage(), e);
        }
    }


    @Step("Sayfada <key> elementinin görünür olana kadar beklenir.")
    public void waitForElementToBeVisible(String key) {
        try {
            By locator = getBy(key);
            if (locator == null) {
                throw new IllegalStateException("Element bulunamadı: " + key);
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element != null && element.isDisplayed()) {
                logger.info(key + " elementinin görünür olmasına kadar beklenildi.");
            } else {
                throw new IllegalStateException(key  + " elementinin görünür olması sağlanamadı.");
            }
        } catch (TimeoutException e) {
            logger.error(key + " elementinin görünür olması için zaman aşımı oluştu.", e);
            throw new IllegalStateException(key + " elementinin görünür olması için zaman aşımı oluştu.", e);
        } catch (Exception e) {
            logger.error("Bekleme sırasında hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException("Bekleme sırasında hata oluştu: " + e.getMessage(), e);
        }
    }
    @Step({"<seconds> saniye bekle", "Wait <second> seconds"})
    public void waitBySecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("<key> elementinde yer alan textler dogrulanir.")
    public void extractAndPrintText(String key) {
        try {
            By locator =getBy(key);
            List<WebElement> elements = driver.findElements(locator);

            for (WebElement element : elements) {
                String text = element.getText();
                if (!text.isEmpty()) {
                    logger.info(text);
                }
            }
        } catch (Exception e) {
            System.err.println("Metin alınırken hata oluştu: " + e.getMessage());
            throw new IllegalStateException("Metin alınırken hata oluştu: " + e.getMessage(), e);
        }
    }

}
