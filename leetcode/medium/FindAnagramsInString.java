// https://leetcode.com/problems/find-all-anagrams-in-a-string/
// Problem: Find All Anagrams in a String
// Difficulty: Medium
// Tags: Hash Table, String, Sliding Window

import java.util.*;

public class FindAnagramsInString {

    // Solution 1: Sliding window with HashMaps
    // Time: O(n), Space: O(1)
    public List<Integer> findAnagramsMap(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        // Build p frequency map
        for (char ch : p.toCharArray()) {
            pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
        }

        int windowSize = p.length();

        // Build first window
        for (int i = 0; i < windowSize; i++) {
            char ch = s.charAt(i);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);
        }

        // check initial window
        if (sMap.equals(pMap)) result.add(0);

        // Slide window
        for (int i = windowSize; i < s.length(); i++) {
            char inChar = s.charAt(i);
            char outChar = s.charAt(i - windowSize);

            // add new character (right end)
            sMap.put(inChar, sMap.getOrDefault(inChar, 0) + 1);

            // Remove old character (left end)
            if (sMap.get(outChar) == 1) sMap.remove(outChar);
            else sMap.put(outChar, sMap.get(outChar) - 1);

            // compare maps
            if (sMap.equals(pMap)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }

    // Solution 2: Optimized using fixed-size array
    // Time: O(n), Space: O(1)
    public List<Integer> findAnagramsArray(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        // build frequency array for p
        for (char ch : p.toCharArray()) {
            pCount[ch - 'a']++;
        }

        // initialize first window
        for (int i = 0; i < p.length(); i++) {
            windowCount[s.charAt(i) - 'a']++;
        }

        // compare first window
        if (Arrays.equals(pCount, windowCount)) result.add(0);

        // slide the window
        for (int i = p.length(); i < s.length(); i++) {
            windowCount[s.charAt(i) - 'a']++; // add new char
            windowCount[s.charAt(i - p.length()) - 'a']--; // removve leftmost char of previous window

            if (Arrays.equals(pCount, windowCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    // Sample Test
    public static void main(String[] args) {
        FindAnagramsInString sol = new FindAnagramsInString();
        System.out.println(sol.findAnagramsMap("cbaebabacd", "abc")); // [0, 6]
        System.out.println(sol.findAnagramsArray("abab", "ab"));      // [0, 1, 2]
    }
}
