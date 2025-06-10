// https://leetcode.com/problems/longest-palindrome/
// Problem: Longest Palindrome
// Difficulty: Easy
// Tags: Hash Table, Greedy, String

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalindrome {

    // Solution 1: Using HashSet to count pairs
    // Time: O(n), Space: O(k)
    public int longestPalindromeSet(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;

        // look for pairs, while looping
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
                count += 2; // a pair
            } else {
                set.add(ch);
            }
        }

        // // If set is not empty, add one odd character in the middle
        if (!set.isEmpty()) {
            count += 1;
        }

        return count;
    }

    // Solution 2: Using HashMap to count frequencies
    // Time: O(n), Space: O(k)
    public int longestPalindromeMap(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // Step 1: Count frequencies
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        int count = 0;
        boolean hasOdd = false;

        // Step 2: Use frequencies to count valid palindrome length
        for (int freq : freqMap.values()) {
            if (freq % 2 == 0) {
                count += freq;
            } else {
                count += freq - 1;
                hasOdd = true;
            }
        }

        // Step 3: Add one center character if there was any odd count
        if (hasOdd) {
            count += 1;
        }

        return count;
    }

    // Sample Test
    public static void main(String[] args) {
        LongestPalindrome solution = new LongestPalindrome();

        String input = "abccccdd";
        System.out.println("Longest Palindrome (Set): " + solution.longestPalindromeSet(input));  // Output: 7
        System.out.println("Longest Palindrome (Map): " + solution.longestPalindromeMap(input));  // Output: 7
    }
}
