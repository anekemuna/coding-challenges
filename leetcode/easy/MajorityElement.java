// https://leetcode.com/problems/majority-element/description/
// Problem: Majority Element
// Difficulty: Easy
// Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.

import java.util.Arrays;

public class MajorityElement {

    // First Solution: Sort the array and return the middle element
    // Time Complexity: O(n log n) due to sorting
    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // Second Solution: Applying Boyer-Moore Voting Algorithm
    // Time Complexity: O(n) and Space Complexity: O(1)
    public int majorityElementBoyerMoore(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement solver = new MajorityElement();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};

        System.out.println("Sort method: " + solver.majorityElementSort(nums.clone()));
        System.out.println("Boyer-Moore method: " + solver.majorityElementBoyerMoore(nums.clone()));
    }
}
