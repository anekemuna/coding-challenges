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

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    static class TimeMapWithList {
        // Map each key to a list of (timestamp, value) pairs
        private Map<String, List<Pair <Integer, String>>> map;
        
        public TimeMapWithList() {
            // Initialize the map
            map = new HashMap<>();
        }

        // Stores the key and value, along with the given timestamp
        public void set(String key, String value, int timestamp) {
            // If key doesn't exist, create a new list for its entries
            map.putIfAbsent(key, new ArrayList<>());

            // Append the (timestamp, value) pair to the list
            // Assumes timestamps for a key are inserted in increasing order
            map.get(key).add(new Pair<>(timestamp, value));
        }

        // Returns the value associated with key at the largest timestamp <= given timestamp
        public String get(String key, int timestamp) {
            // If the key does not exist, return empty string
            if (!map.containsKey(key)) return "";

            List<Pair<Integer, String>> list = map.get(key);

            // Binary search to find the closest timestamp <= target timestamp
            int left = 0, right = list.size() - 1;
            String result = "";  // Default if no valid timestamp found

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midTimestamp = list.get(mid).getKey();

                if (midTimestamp <= timestamp) {
                    // Current mid timestamp is valid, save value and try to find a closer one to the right
                    result = list.get(mid).getValue();
                    left = mid + 1;
                } else {
                    // midTimestamp > target, discard right half
                    right = mid - 1;
                }
            }

            return result;
        }
    }


    // Sample Test
    public static void main(String[] args) {
        TimeMap obj = new TimeMap();
        TimeMapWithList objWithList = new TimeMapWithList();

        System.out.println("Solution with a Sorted Map (TreeMap)");
        obj.set("foo", "bar", 1);
        System.out.println("Get foo at 1: " + obj.get("foo", 1));  // Output: bar
        System.out.println("Get foo at 3: " + obj.get("foo", 3));  // Output: bar

        obj.set("foo", "bar2", 4);
        System.out.println("Get foo at 4: " + obj.get("foo", 4));  // Output: bar2
        System.out.println("Get foo at 5: " + obj.get("foo", 5));  // Output: bar2

        System.out.println("Get non-existent key at 1: " + obj.get("baz", 1));  // Output: ""

        System.out.println("\nSolution with a List and Custom Pair");
        objWithList.set("foo", "bar", 1);
        System.out.println("Get foo at 1: " + objWithList.get("foo", 1));  // Output: bar
        System.out.println("Get foo at 3: " + objWithList.get("foo", 3));  // Output: bar

        objWithList.set("foo", "bar2", 4);
        System.out.println("Get foo at 4: " + objWithList.get("foo", 4));  // Output: bar2
        System.out.println("Get foo at 5: " + objWithList.get("foo", 5));  // Output: bar2

        System.out.println("Get non-existent key at 1: " + objWithList.get("baz", 1));  // Output: ""

    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */