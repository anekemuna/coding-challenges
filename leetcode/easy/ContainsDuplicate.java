// https://leetcode.com/problems/contains-duplicate/
// Problem: Contains Duplicate
// Difficulty: Easy
// Tag: Array, HashSet, Sorting
// Given an integer array, determine if any value appears at least twice.

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    // Solution 1: Sort and check adjacent elements
    // Time Complexity: O(n log n) due to sorting
    // Space Complexity: O(1) (or O(n) if sorting is not in-place)
    public boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    // Solution 2: Use a HashMap to track occurrences
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean containsDuplicateHashMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                return true;  // Duplicate found
            }
            map.put(num, 1);
        }
        return false;
    }

    // Alternative HashSet solution (preferred)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean containsDuplicateHashSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;  // Duplicate found
            }
        }
        return false;
    }

    // Main method for quick local testing
    public static void main(String[] args) {
        ContainsDuplicate solver = new ContainsDuplicate();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 3, 4};

        System.out.println("\n   Sort method (nums1): " + solver.containsDuplicateSort(nums1));      // true
        System.out.println("HashMap method (nums1): " + solver.containsDuplicateHashMap(nums1));  // true
        System.out.println("HashSet method (nums1): " + solver.containsDuplicateHashSet(nums1));  // true

        System.out.println("\n   Sort method (nums2): " + solver.containsDuplicateSort(nums2));      // false
        System.out.println("HashMap method (nums2): " + solver.containsDuplicateHashMap(nums2));  // false
        System.out.println("HashSet method (nums2): " + solver.containsDuplicateHashSet(nums2));  // false
    }
}
