package definitions;

import com.thoughtworks.gauge.Step;
import org.apache.log4j.LogManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.DriverSettings;
import utils.ElementStorage;
import utils.SelectorHelper;
import org.apache.log4j.Logger;
import utils.ValueStorage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
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

    @Step("<key> elementindeki degeri hafizaya kaydet")
    public void saveCurrentAmount(String key) {
        try {
            // Selector'ü al
            By selector = getBy(key);

            // Elementi bul
            WebElement amountElement = driver.findElement(selector);
            if (amountElement == null) {
                throw new NullPointerException("Element not found for key: " + key);
            }

            // Elementin text değerini al
            String amountText = amountElement.getText();
            if (amountText == null || amountText.isEmpty()) {
                throw new NullPointerException("Text is null or empty for key: " + key);
            }

            logger.info("Hafizaya kaydedilen deger (orijinal): " + amountText);

            // AmountText'i parse ederek sayısal değere dönüştür ve hafızaya kaydet
            double amount = parsedAmount(amountText);
            logger.info("Hafizaya kaydedilen deger (numeric): " + amount);

            // Hafızaya kaydet
            ValueStorage.storeValue(key, amount);

        } catch (Exception e) {
            logger.error("Error while saving current amount for key: " + key, e);
            throw e; // Hatanın Gauge tarafından raporlanması için tekrar fırlatılır
        }
    }

    private double parsedAmount(String amountText) {
        try {
            // Virgülleri kaldır
            amountText = amountText.replace(",", "");

            // Sayıyı parse et
            double amount = Double.parseDouble(amountText);

            // Dönen sayıyı geri döndür
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing amount: " + amountText, e);
        }
    }
    // String olarak gelen para miktarını düzgün formatta dönüştürme metodu
    private double parseAmount(String amountText) {
        try {
            // Virgülleri kaldır, sadece sayıyı almak için
            String cleanedText = amountText.replaceAll("[^0-9,.-]", ""); // Sadece sayılar, virgül ve nokta kalsın

            // Eğer sayı formatı İngiltere/ABD formatıysa, noktayı ondalık olarak kabul et
            if (cleanedText.contains(",")) {
                if (cleanedText.lastIndexOf(',') > cleanedText.indexOf('.')) {
                    // Virgül sonradan geliyorsa, muhtemelen sayı Avrupa formatında
                    cleanedText = cleanedText.replaceAll(",", "");
                } else {
                    // Virgül ondalık işareti, onun yerine nokta kullanacağız
                    cleanedText = cleanedText.replace(",", ".");
                }
            }

            // Sayıyı double'a çevir
            return Double.parseDouble(cleanedText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing amount: " + amountText, e);
        }
    }

    @Step("<key> elementindeki eklenen tutari <value> olarak kaydet")
    public void saveAddedAmount(String key, double value) {
        ValueStorage.storeValue(key, value);
    }

    @Step("Transfer icin <initialKey> de ki baslangic tutarini alip eklenen miktar <addedKey> ile karsilastir ve transfer alanını <transferAmount> kontrol et.")
    public void logDifference(String initialKey, String addedKey,String transferAmount) {
        double initialAmount = ValueStorage.getValue(initialKey);
        double addedAmount = ValueStorage.getValue(addedKey);
        By locator=getBy(transferAmount);
        WebElement element = driver.findElement(locator);
       double transfer= Double.parseDouble(element.getText());

        double actualDifference = initialAmount - addedAmount;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00");

        logger.info("Initial amount: $" + decimalFormat.format(initialAmount));
        logger.info("Added amount: $" + decimalFormat.format(addedAmount));
        logger.info("Difference: $" + decimalFormat.format(actualDifference));
        logger.info("Transfer Amount: $" + decimalFormat.format(transfer));
        assertThat(transfer)
                .withFailMessage("Değerler eşit değil! Transfer Amount: %s, Amount: %s", transfer, addedAmount)
                .isEqualTo(addedAmount);
    }
    @Step("Add money icin <initialKey> de ki baslangic tutarini alip eklenen miktar <addedKey> ile karsilastir")
    public void addMoneyDifference(String initialKey, String addedKey) {

        double initialAmount = ValueStorage.getValue(initialKey);
        double addedAmount = ValueStorage.getValue(addedKey);
        double expectedAmount = initialAmount + addedAmount;
        double actualDifference = initialAmount - addedAmount;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00");
        saveCurrentAmount("amountTextControl");
        double lastAmount = ValueStorage.getValue(initialKey);

        logger.info("Initial amount: $" + decimalFormat.format(initialAmount));
        logger.info("Added amount: $" + decimalFormat.format(addedAmount));
        logger.info("Difference: $" + decimalFormat.format(actualDifference));
        logger.info("Expected Amount: $" + decimalFormat.format(expectedAmount));
        waitBySecond(1);
        assertThat(lastAmount)
                .withFailMessage("Değerler eşit değil! Last Amount: %s, Expected Amount: %s",decimalFormat.format(lastAmount), decimalFormat.format(expectedAmount))
                .isEqualTo(expectedAmount);
    }

 @Step("<key> elementinde yer alan seçeneklerden <text> degerini sec")
    public void selectByText(String key,String text) {
        try {
            By locator = getBy(key);
            WebElement element = driver.findElement(locator);

            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(text);
            logger.info("Secilen secenek :  " + text);
        }
        catch (Exception e) {
            logger.error("Element metni doğrulanırken hata oluştu: " + e.getMessage(), e);
            throw new IllegalStateException("Element metni doğrulanırken hata oluştu: " + e.getMessage(), e);
        }

    }


    @Step("Bakiyeyi <key> elementinden al ve kontrol et")
    public void validateBakiye(String key) throws ParseException {

        By locator=getBy(key);
        WebElement bakiyeElement = driver.findElement(locator);
        String bakiyeText = bakiyeElement.getText();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00");

        Number number = decimalFormat.parse(bakiyeText);
        double bakiye = number.doubleValue();

        try {
            if (bakiye > 0) {
                logger.info("Bakiye geçerli: " + bakiye);
            } else {
                logger.info("Bakiye geçersiz! Değer: " + bakiye);
                throw new AssertionError("Bakiye geçersiz. Değer: " + bakiye);
            }
        } catch (NumberFormatException e) {
            System.err.println("Bakiye değeri sayısal bir formatta değil: " + bakiyeText);
            throw new AssertionError("Bakiye değeri sayısal bir formatta değil: " + bakiyeText, e);
        }
    }

}
