// https://leetcode.com/problems/valid-anagram/
// Problem: Valid Anagram
// Difficulty: Easy
// Tag: HashMap, Sorting, Array

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    // Solution 1: Sort and Compare
    // Time: O(n log n), Space: O(n)
    public boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        return Arrays.equals(sArray, tArray);
    }

    // Solution 2: HashMap Frequency Count
    // Time: O(n), Space: O(n)
    public boolean isAnagramMap(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        return sMap.equals(tMap);
    }

    // Solution 3: Optimized Count Array (Only lowercase letters)
    // Time: O(n), Space: O(1)
    public boolean isAnagramOptimized(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // for lowercase a-z

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }

        return true;
    }

    // Main method to test all implementations
    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        String s = "anagram", t = "nagaram";

        System.out.println("Sort: " + solution.isAnagramSort(s, t));        // true
        System.out.println("HashMap: " + solution.isAnagramMap(s, t));      // true
        System.out.println("Optimized: " + solution.isAnagramOptimized(s, t)); // true
    }
}
