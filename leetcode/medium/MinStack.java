// https://leetcode.com/problems/min-stack/
// Problem: Min Stack
// Difficulty: Medium
// Tag: Stack, Design

import java.util.Stack;

public class MinStack {
    private Stack<Integer> mainStack;  // Stores all elements
    private Stack<Integer> minStack;   // Stores current minimum at each level

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    // Push value to stack and track min value
    public void push(int val) {
        mainStack.push(val);

        // Only push to minStack if it's empty or val is <= current min
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // Pop from both stacks if popped element is the current min
    public void pop() {
        int popped = mainStack.pop();
        if (!minStack.isEmpty() && popped == minStack.peek()) {
            minStack.pop();
        }
    }

    // Return the top element
    public int top() {
        return mainStack.peek();
    }

    // Return the current minimum element
    public int getMin() {
        return minStack.peek();
    }

    // Sample test case for the MinStack
    public static void main(String[] args) {
        MinStack stack = new MinStack();

        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        System.out.println("Current min: " + stack.getMin()); // Output: -3
        stack.pop();

        System.out.println("Top element: " + stack.top());     // Output: 0
        System.out.println("Current min: " + stack.getMin()); // Output: -2
    }
}
