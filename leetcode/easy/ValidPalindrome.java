// https://leetcode.com/problems/valid-palindrome/
// Problem: Valid Palindrome
// Difficulty: Easy
// Tag: Two Pointers, String
// Determine if a string is a palindrome, considering only alphanumeric characters and ignoring cases.

public class ValidPalindrome {

    // Solution 1: Filter and compare
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean isPalindromeFilter(String s) {
        // remove non-alphanumeric characters and convert to lowercase
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)){ return false;}
        }

        return true;
    }

    // Solution 2: Two pointers (optimized)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean isPalindromeTwoPointers(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;

            left++;
            right--;
        }

        return true;
    }

    // Main method to test both solutions
    public static void main(String[] args) {
        ValidPalindrome solver = new ValidPalindrome();

        String input = "A man, a plan, a canal: Panama";
        String input2 = "race a car";

        System.out.println("Input 1: \"" + input + "\"");
        System.out.println("Using Filter method: " + solver.isPalindromeFilter(input)); // true
        System.out.println("Using Two Pointers method: " + solver.isPalindromeTwoPointers(input)); // true

        System.out.println("\nInput 2: \"" + input2 + "\"");
        System.out.println("Using Filter method: " + solver.isPalindromeFilter(input2)); // false
        System.out.println("Using Two Pointers method: " + solver.isPalindromeTwoPointers(input2)); // false
    }
}
