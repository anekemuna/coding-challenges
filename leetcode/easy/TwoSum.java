// https://leetcode.com/problems/two-sum/
// Problem: Two Sum
// Difficulty: Easy
// Tag: Array, HashMap
// Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    // Solution: Use a HashMap to store complements
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        // According to the problem description, a valid answer always exists
        return new int[] {};
    }

    // Main method for quick testing
    public static void main(String[] args) {
        TwoSum solver = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = solver.twoSum(nums, target);
        System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
    }
}
