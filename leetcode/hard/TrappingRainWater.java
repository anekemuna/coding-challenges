// https://leetcode.com/problems/trapping-rain-water/
// Problem: Trapping Rain Water
// Difficulty: Hard
// Tag: Array, Two Pointers, Dynamic Programming, Stack

import java.util.Stack;

public class TrappingRainWater {

    // Solution 1: Prefix Max Arrays
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int trapWithExtraSpace(int[] height) {
        int water = 0;
        int[] leftMax = new int[height.length], rightMax = new int[height.length];

        // Left max for every index
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        // Right max for every index
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        // Water trapped at each index
        for (int i = 0; i < height.length; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return water;
    }

    // Solution 2: Optimized Two Pointers
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int trapOptimized(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }

    // Solution 3: Implemented with Stack
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int trapStack(int [] height) {
        int waterTrapped = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        while (current < height.length) {
            // While current bar is taller than the bar at stack top
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int bottom = stack.pop(); // The valley bottom

                // If the stack is empty, no left boundary exists
                if (stack.isEmpty()) break;

                int left = stack.peek(); // Left boundary
                int width = current - left - 1; // Width between current and left
                int boundedHeight = Math.min(height[current], height[left]) - height[bottom];

                waterTrapped += width * boundedHeight;
            }

            stack.push(current);
            current++;
        }

        return waterTrapped;
    }

    // Sample test
    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println("Trapped water (extra space): " + solution.trapWithExtraSpace(heights)); // Output: 6
        System.out.println("Trapped water (optimized): " + solution.trapOptimized(heights));       // Output: 6
        System.out.println("Trapped water (stack): " + solution.trapStack(heights));
    }
}
