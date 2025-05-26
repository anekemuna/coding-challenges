package leetcode.medium;

// https://leetcode.com/problems/3sum/
// Problem: 3Sum
// Difficulty: Medium
// Tag: Array, Two Pointers, Sorting

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // Find all unique triplets that sum to 0
    // Time Complexity: O(n^2)
    // Space Complexity: O(n) (for result and sorting)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);  // Sort first

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate numbers for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSum solver = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = solver.threeSum(nums);
        System.out.println("3Sum Results:");
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}

