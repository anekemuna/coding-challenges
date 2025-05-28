// https://leetcode.com/problems/container-with-most-water/
// Problem: Container With Most Water
// Difficulty: Medium
// Tag: Array, Two Pointers, Greedy
// Given n non-negative integers where each represents a point at coordinate (i, height[i]),
// n vertical lines are drawn. Find two lines that together with the x-axis form a container,
// such that the container contains the most water.

public class ContainerWithMostWater {

    // Two-pointer approach
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1, maxArea = 0;

        while (start < end) {
            int tempArea = Math.min(height[start], height[end]) * (end - start);
            maxArea = Math.max(maxArea, tempArea);

            // Move the pointer pointing to the shorter line
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solver = new ContainerWithMostWater();
        int[] heights1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int [] heights2 = {1, 1};

        int result = solver.maxArea(heights1);
        System.out.println("Maximum area 1: " + result);  // Expected: 49
        result = solver.maxArea(heights2);
        System.out.println("Maximum area 2: " + result);  // Expected: 1
    }
}
