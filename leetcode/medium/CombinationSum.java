// https://leetcode.com/problems/combination-sum/
// Problem: Combination Sum
// Difficulty: Medium
// Tag: Array, Backtracking
// Given an array of distinct integers (candidates) and a target integer,
// return all unique combinations of candidates where the chosen numbers sum to target.
// The same number may be chosen unlimited times.

package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    // Recursive backtracking approach
    // Time Complexity: O(2^t) where t is the target (in worst case)
    // Space Complexity: O(t) for recursion stack and O(result size) for output
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }

    // Helper method for recursive backtracking
    private void backtrack(int[] candidates, int target, int start, List<Integer> temp, List<List<Integer>> results) {
        // Base case: exact match
        if (target == 0) {
            results.add(new ArrayList<>(temp));  // Make a copy and store it
            return;
        }

        // Base case: overshoot
        if (target < 0) {
            return;
        }

        // Explore choices
        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);  // Choose current candidate
            backtrack(candidates, target - candidates[i], i, temp, results);  // Not i+1 since we can reuse same element
            temp.remove(temp.size() - 1);  // Backtrack
        }
    }

    // Main method for quick testing
    public static void main(String[] args) {
        CombinationSum solver = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = solver.combinationSum(candidates, target);
        System.out.println("Combinations that sum to " + target + ":");
        for (List<Integer> combo : result) {
            System.out.println(combo);
        }
    }
}
