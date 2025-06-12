// https://leetcode.com/problems/minimum-window-substring/
// Problem: Minimum Window Substring
// Difficulty: Hard
// Tags: Hash Table, String, Sliding Window, Two Pointers

import java.util.*;

public class MinimumWindowSubstring {

    // Solution 1: HashMap-based (Unicode-safe)
    // Time: O(s + t), Space: O(k) where k = number of unique characters
    public String minWindowMap(String s, String t) {
        //base case
        if (t.length() > s.length()) return "";

        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        // create map of string t
        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        int required = tMap.size(), // map count
            formed = 0; // number of unique characters in current window with correct frequency
        int left = 0, right = 0, minLength = Integer.MAX_VALUE;
        String result = "";

        // sliding window
        while (right < s.length()) {
            char ch = s.charAt(right);
            sMap.put(ch, sMap.getOrDefault(ch, 0) + 1);

            if (tMap.containsKey(ch) && sMap.get(ch).equals(tMap.get(ch))) {
                formed++;
            }

             // Try to shrink window from left while it's valid
            while (formed == required) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    result = s.substring(left, right + 1);
                }

                char leftChar = s.charAt(left);

                // remove left char from window map
                sMap.put(leftChar, sMap.get(leftChar) - 1);
                if (sMap.get(leftChar) == 0) sMap.remove(leftChar);

                if (tMap.containsKey(leftChar) && sMap.getOrDefault(leftChar, 0) < tMap.get(leftChar)) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return result;
    }

    // Solution 2: Optimized using ASCII array
    // Time: O(s + t), Space: O(1) — fixed-size arrays for ASCII
    public String minWindowArray(String s, String t) {
        // base case
        if (s.length() < t.length()) return "";

        // ASCII
        int[] tFreq = new int[128];
        int[] windowFreq = new int[128];

        // Fill character counts from t
        for (char c : t.toCharArray()) {
            tFreq[c]++;
        }

        int required = 0;
        for (int count : tFreq) {
            if (count > 0) required++;
        }

        int left = 0, right = 0, formed = 0;
        int minLen = Integer.MAX_VALUE, minStart = 0;

        // sliding window
        while (right < s.length()) {
            char ch = s.charAt(right);
            windowFreq[ch]++;

            // Count this char as formed if freq matches
            if (tFreq[ch] > 0 && windowFreq[ch] == tFreq[ch]) {
                formed++;
            }

            // Try to shrink the window while it's valid
            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                windowFreq[leftChar]--;

                if (tFreq[leftChar] > 0 && windowFreq[leftChar] < tFreq[leftChar]) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // Sample test
    public static void main(String[] args) {
        MinimumWindowSubstring sol = new MinimumWindowSubstring();
        System.out.println(sol.minWindowMap("ADOBECODEBANC", "ABC"));  // BANC
        System.out.println(sol.minWindowArray("a", "a"));              // a
    }
}
