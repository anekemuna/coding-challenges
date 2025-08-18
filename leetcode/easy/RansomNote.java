// https://leetcode.com/problems/ransom-note/
// Problem: Ransom Note
// Difficulty: Easy
// Tags: Hash Table, String, Counting

import java.util.*;

public class RansomNote {

    // Solution 1: Using Maps
    // Time: O(n + m), Space: O(n + m)
    public boolean canConstructUsingMaps(String ransomNote, String magazine) {
        Map<Character, Integer> magazineMap = new HashMap<>();
        Map<Character, Integer> noteMap = new HashMap<>();

        // populate magazine frequency map
        for (char c : magazine.toCharArray()) {
            magazineMap.put(c, magazineMap.getOrDefault(c, 0) + 1);
        }

        // check ransom note validity while tracking usage
        for (char c : ransomNote.toCharArray()) {
            if (!magazineMap.containsKey(c)) return false;

            int count = noteMap.getOrDefault(c, 0) + 1;
            if (count > magazineMap.get(c)) return false;
            noteMap.put(c, count);
        }

        return true;
    }

    // Solution 2: Using Array (Optimal)
    // Time: O(n + m), Space: O(1)
    public boolean canConstructUsingArray(String ransomNote, String magazine) {
        int[] freq = new int[26]; // only lowercase letters

        for (char c : magazine.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (freq[c - 'a'] == 0) return false; // If there's no more of that character in magazine, return false
            freq[c - 'a']--;// Decrease the frequency of that character
        }

        return true;
    }

    // Sample Test
    public static void main(String[] args) {
        RansomNote solution = new RansomNote();

        System.out.println("Maps: " + solution.canConstructUsingMaps("a", "b"));   // false
        System.out.println("Maps: " + solution.canConstructUsingMaps("aa", "aab")); // true
        System.out.println("Array: " + solution.canConstructUsingArray("a", "b"));   // false
        System.out.println("Array: " + solution.canConstructUsingArray("aa", "aab")); // true
    }
}
