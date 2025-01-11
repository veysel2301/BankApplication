package utils;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementStorage {
    private static WebDriver driver;
    private static HashMap<String, WebElement> elementMap = new HashMap<>();

    public ElementStorage(WebDriver driver) {
        this.driver = driver;
    }

    public static WebElement storeElement(String key, By locator) {
        WebElement element = driver.findElement(locator);
        elementMap.put(key, element);
        return element;
    }

    public static WebElement getElement(String key) {
        return elementMap.get(key);
    }
}
