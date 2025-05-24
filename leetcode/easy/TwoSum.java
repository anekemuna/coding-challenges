// https://leetcode.com/problems/two-sum/
// Problem: Two Sum
// Difficulty: Easy

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        // In case no solution is found, though per problem spec one always exists
        return new int[] {};
    }
}
