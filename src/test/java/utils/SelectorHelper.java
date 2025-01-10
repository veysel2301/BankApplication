package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SelectorHelper {

    private static final Logger logger = Logger.getLogger(SelectorHelper.class);
    private static Map<String, Map<String, String>> elements;


    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            File jsonFile = new File("src/test/resources/JsonFile/selectors.json");
            elements = objectMapper.readValue(jsonFile, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("JSON dosyası yüklenirken hata oluştu: " + e.getMessage(), e);
        }
    }


    public static By getBy(String elementName) {
        Map<String, String> elementData = elements.get(elementName);
        if (elementData == null) {
            throw new IllegalArgumentException("Element bulunamadı: " + elementName);
        }

        String type = elementData.get("type");
        String value = elementData.get("value");

        if (type == null || value == null) {
            throw new IllegalArgumentException("Element tanımı eksik: " + elementName);
        }

        switch (type.toLowerCase()) {
            case "id":
                return By.id(value);
            case "name":
                return By.name(value);
            case "css":
                return By.cssSelector(value);
            case "xpath":
                return By.xpath(value);
            case "class":
                return By.className(value);
            case "tag":
                return By.tagName(value);
            case "linktext":
                return By.linkText(value);
            case "partiallinktext":
                return By.partialLinkText(value);
            default:
                throw new IllegalArgumentException("Desteklenmeyen locator tipi: " + type);
        }
    }
}

