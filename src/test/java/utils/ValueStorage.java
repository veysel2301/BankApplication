package utils;

import java.util.HashMap;

public class ValueStorage {
    private static HashMap<String, Double> valueMap = new HashMap<>();

    public static void storeValue(String key, double value) {
        valueMap.put(key, value);
    }

    public static double getValue(String key) {
        return valueMap.get(key);
    }
}
