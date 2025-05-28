// https://leetcode.com/problems/product-of-array-except-self/
// Problem: Product of Array Except Self
// Difficulty: Medium
// Tag: Array, Prefix Sum
// Given an integer array nums, return an array answer such that answer[i] is equal to 
// the product of all the elements of nums except nums[i].
// The solution must be done without using division and in O(n) time.

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    // ✅ Solution 1: Prefix and Suffix Arrays
    // Time Complexity: O(n)
    // Space Complexity: O(n) extra (excluding output)
    public int[] productExceptSelfExtraSpace(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] answer = new int[n];

        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = nums[i + 1] * suffix[i + 1];
        }

        for (int i = 0; i < n; i++) {
            answer[i] = prefix[i] * suffix[i];
        }

        return answer;
    }

    // Solution 2: Optimized with O(1) extra space (excluding output)
    // Time Complexity: O(n)
    // Space Complexity: O(1) extra
    public int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Build prefix products in output array
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // Multiply with suffix product on the fly
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }

        return answer;
    }

    // Main method for quick testing
    public static void main(String[] args) {
        ProductOfArrayExceptSelf solver = new ProductOfArrayExceptSelf();
        int[] nums = {1, 2, 3, 4};

        System.out.println("Extra space: " + Arrays.toString(solver.productExceptSelfExtraSpace(nums)));
        System.out.println("Optimized: " + Arrays.toString(solver.productExceptSelfOptimized(nums)));
    }
}
