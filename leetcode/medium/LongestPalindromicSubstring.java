// https://leetcode.com/problems/longest-palindromic-substring/
// Problem: Longest Palindromic Substring
// Difficulty: Medium
// Tags: String, Dynamic Programming, Expand Around Center

public class LongestPalindromicSubstring {

    // Solution 1: Expand Around Center (Manual Variant)
    // Time: O(n^2), Space: O(1)
    public String longestPalindromeManual(String s) {
        
        // if string length is 1, return string
        if (s.length() == 1) return s;

        int len = s.length();
        int left, right;
        String result = "", temp = "";

        for (int i = 0; i < len; i++) {
            // Even length palindrome
            left = i;
            right = i + 1;

            // expand left and right until no match
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                temp = s.substring(left, right + 1);
                left--;
                right++;
            }
            if (temp.length() > result.length()) result = temp;

            // Odd length palindrome
            temp = "";
            left = i;
            right = i;
            
            // expand left and right until no match
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                temp = s.substring(left, right + 1);
                left--;
                right++;
            }
            if (temp.length() > result.length()) result = temp;
        }

        return result;
    }

    // Solution 2: Clean Expand Around Center with helper
    // Time: O(n^2), Space: O(1)
    public String longestPalindromeClean(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand around odd-length center (single character)
            int len1 = expand(s, i, i);     // Odd length
            // Expand around even-length center (between two characters)
            int len2 = expand(s, i, i + 1); // Even length
            int len = Math.max(len1, len2);

            // Update start and end pointers if a longer palindrome is found
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // Expands around a given center and returns the length of the palindrome
    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // Final palindrome is between indices (left+1) to (right-1)
        return right - left - 1;
    }

    // Sample Test
    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        System.out.println(solution.longestPalindromeManual("babad")); // Output: "bab" or "aba"
        System.out.println(solution.longestPalindromeClean("cbbd"));   // Output: "bb"
    }
}
