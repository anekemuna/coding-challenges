// https://leetcode.com/problems/largest-rectangle-in-histogram/
// Problem: Largest Rectangle in Histogram
// Difficulty: Hard
// Tag: Stack, Array, Monotonic Stack

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();  // Stack stores indices of bars
        int maxArea = 0;

        // Iterate through all bars plus one extra iteration for sentinel
        for (int i = 0; i <= n; i++) {
            // Current height, 0 if at the sentinel iteration
            int currHeight = (i == n) ? 0 : heights[i];

            // Pop bars from stack while current bar is lower than top of stack
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                // Pop the top of stack (bar index)
                int height = heights[stack.pop()];

                // Calculate width of rectangle with this height
                // If stack is empty, width is i (all bars before)
                // Else, width is difference between current index and index after top of stack - 1
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                // Calculate area and update maxArea
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }

            // Push current index onto stack
            stack.push(i);
        }

        return maxArea;
    }

    // Test main method
    public static void main(String[] args) {
        LargestRectangleHistogram solution = new LargestRectangleHistogram();

        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + solution.largestRectangleArea(heights1)); // Expected: 10

        int[] heights2 = {2, 4};
        System.out.println("Largest Rectangle Area: " + solution.largestRectangleArea(heights2)); // Expected: 4

        int[] heights3 = {6, 2, 5, 4, 5, 1, 6};
        System.out.println("Largest Rectangle Area: " + solution.largestRectangleArea(heights3)); // Expected: 12
    }
}
