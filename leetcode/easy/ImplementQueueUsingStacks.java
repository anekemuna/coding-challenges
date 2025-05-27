// https://leetcode.com/problems/implement-queue-using-stacks/
// Problem: Implement Queue using Stacks
// Difficulty: Easy
// Tag: Stack, Queue, Design

import java.util.Stack;

public class ImplementQueueUsingStacks {

    // Solution 1: Original Approach
    // Each pop() or peek() fully reverses the stack to simulate queue behavior.
    // push() → O(1), pop() → O(n), peek() → O(n), empty() → O(1)
    static class MyQueueOriginal {
        private Stack<Integer> frontStack;
        private Stack<Integer> backStack;

        public MyQueueOriginal() {
            this.frontStack = new Stack<>();
            this.backStack = new Stack<>();
        }

        // Push to back of the queue
        public void push(int x) {
            frontStack.push(x);
        }

        // Remove from front of the queue
        public int pop() {
            // Reverse order: move elements to backStack
            while (!frontStack.isEmpty()) {
                backStack.push(frontStack.pop());
            }

            // Remove front element
            int value = backStack.pop();

            // Restore original order
            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }

            return value;
        }

        // Get front element without removing it
        public int peek() {
            // Reverse order: move elements to backStack
            while (!frontStack.isEmpty()) {
                backStack.push(frontStack.pop());
            }

            int value = backStack.peek();

            // Restore original order
            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }

            return value;
        }

        // Check if queue is empty
        public boolean empty() {
            return frontStack.isEmpty() && backStack.isEmpty();
        }
    }

    // Solution 2: Optimized Approach
    // Lazy-load strategy: only move elements when needed (amortized O(1) operations)
    // push() → O(1), pop() → Amortized O(1), peek() → Amortized O(1), empty()` → O(1)
    static class MyQueueOptimized {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;

        public MyQueueOptimized() {
            inputStack = new Stack<>();
            outputStack = new Stack<>();
        }

        // Push to back of the queue
        public void push(int x) {
            inputStack.push(x);
        }

        // Remove from front of the queue
        public int pop() {
            // If output stack is empty, transfer from input
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop();
        }

        // Peek front element without removing
        public int peek() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }

        // Check if queue is empty
        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }

    // Main method for testing both implementations
    public static void main(String[] args) {
        System.out.println("=== MyQueueOriginal ===");
        MyQueueOriginal queue1 = new MyQueueOriginal();
        queue1.push(1);
        queue1.push(2);
        System.out.println("Peek: " + queue1.peek());   // Output: 1
        System.out.println("Pop: " + queue1.pop());     // Output: 1
        System.out.println("Empty: " + queue1.empty()); // Output: false

        System.out.println("\n=== MyQueueOptimized ===");
        MyQueueOptimized queue2 = new MyQueueOptimized();
        queue2.push(1);
        queue2.push(2);
        System.out.println("Peek: " + queue2.peek());   // Output: 1
        System.out.println("Pop: " + queue2.pop());     // Output: 1
        System.out.println("Empty: " + queue2.empty()); // Output: false
    }
}
