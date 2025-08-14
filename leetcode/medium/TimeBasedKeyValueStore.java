// https://leetcode.com/problems/time-based-key-value-store/
// Problem: Time Based Key-Value Store
// Difficulty: Medium
// Tags: HashMap, TreeMap, Design

import java.util.*;

public class TimeBasedKeyValueStore {

    static class TimeMap {
        // store key as string, value and timestamp in TreeMap
        private Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            this.map = new HashMap<>();
        }

        // Store the key with value at given timestamp
        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> valueMap;
            if (!map.containsKey(key)) {
                valueMap = new TreeMap<>();
            } else {
                valueMap = map.get(key);
            }
            valueMap.put(timestamp, value);
            map.put(key, valueMap);
        }

        // Retrieve the value with largest timestamp <= given timestamp
        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";

            TreeMap<Integer, String> valueMap = map.get(key);
            Map.Entry<Integer, String> entry = valueMap.floorEntry(timestamp);
            if (entry == null) return "";

            return entry.getValue();
        }
    }

    // Sample Test
    public static void main(String[] args) {
        TimeMap obj = new TimeMap();

        obj.set("foo", "bar", 1);
        System.out.println("Get foo at 1: " + obj.get("foo", 1));  // Output: bar
        System.out.println("Get foo at 3: " + obj.get("foo", 3));  // Output: bar

        obj.set("foo", "bar2", 4);
        System.out.println("Get foo at 4: " + obj.get("foo", 4));  // Output: bar2
        System.out.println("Get foo at 5: " + obj.get("foo", 5));  // Output: bar2

        System.out.println("Get non-existent key at 1: " + obj.get("baz", 1));  // Output: ""
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */