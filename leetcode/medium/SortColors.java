// https://leetcode.com/problems/sort-colors/
// Problem: Sort Colors
// Difficulty: Medium
// Tag: Array, Two Pointers, Sorting
// Given an array with n objects colored red (0), white (1), or blue (2),
// sort them in-place so that objects of the same color are adjacent.

import java.util.Arrays;

public class SortColors {

    // Solution 1: Counting sort (Two-pass)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void sortColorsCount(int[] nums) {
        int zero = 0, one = 0; // two = 0;

        // First pass: count occurrences
        for (int num : nums) {
            if (num == 0) zero++;
            else if (num == 1) one++;
            //else two++;
        }

        // Second pass: overwrite
        for (int i = 0; i < nums.length; i++) {
            if (zero > 0) {
                nums[i] = 0;
                zero--;
            } else if (one > 0) {
                nums[i] = 1;
                one--;
            } else {
                nums[i] = 2;
            }
        }
    }

    // Solution 2: Dutch National Flag Algorithm (One-pass, in-place)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public void sortColorsDNF(int[] nums) {
        int zero = 0, two = nums.length - 1;
        int i = 0;

        while (i <= two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, two);
                two--;
            } else {
                i++;
            }
        }
    }

    // Helper method for swapping
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    // Main method for testing
    public static void main(String[] args) {
        SortColors solver = new SortColors();
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        int[] nums2 = nums1.clone();

        solver.sortColorsCount(nums1);
        System.out.println("Sorted with Counting Sort: " + Arrays.toString(nums1));

        solver.sortColorsDNF(nums2);
        System.out.println("Sorted with Dutch National Flag: " + Arrays.toString(nums2));
    }
}
