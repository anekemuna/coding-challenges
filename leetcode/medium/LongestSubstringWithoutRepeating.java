// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Problem: Longest Substring Without Repeating Characters
// Difficulty: Medium
// Tags: Hash Table, String, Sliding Window

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    // Solution 1: Brute-force with sliding window and copy
    // Time: O(n^2), Space: O(n)
    public int lengthOfLongestSubstringBruteForce(String s) {
        int maxCount = 0; // store max variable
        char[] array = s.toCharArray();// store caracter array of string

        for (int i = 0; i < s.length(); i++) {
            Set<Character> hashset = new HashSet<>();
            int temp = 0;
            // make copy from start index of source (array)
            char[] array2 = Arrays.copyOfRange(array, i, array.length);

            for (char ch : array2) {
                if (hashset.contains(ch)) {
                    break;// break if character alrady exist
                } else {
                    hashset.add(ch);
                    temp++; // increase count if character does not exist
                    //System.out.println(temp + " " + ch);
                }
            }

            maxCount = Math.max(maxCount, temp); // keep max value
        }

        return maxCount;
    }

    // Solution 2: Optimized sliding window with HashSet
    // Time: O(n), Space: O(n)
    public int lengthOfLongestSubstringOptimized(String s) {
        // HashSet to store characters in the current window
        Set<Character> seen = new HashSet<>();
        int left = 0, // Left pointer of the window
            maxLength = 0;

        // Right pointer moves through the string
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

             // If character already in the window, shrink from the left
            while (seen.contains(currentChar)) {
                seen.remove(s.charAt(left));
                left++;
            }

            // Add the new character and update max length
            seen.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Sample Test
    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solution = new LongestSubstringWithoutRepeating();

        String input = "abcabcbb";
        System.out.println("Brute Force: " + solution.lengthOfLongestSubstringBruteForce(input)); // Output: 3
        System.out.println("Optimized: " + solution.lengthOfLongestSubstringOptimized(input));     // Output: 3
    }
}
