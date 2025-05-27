// https://leetcode.com/problems/implement-queue-using-stacks/
// Problem: Implement Queue using Stacks
// Difficulty: Easy
// Tag: Stack, Queue, Design

import java.util.Stack;

public class ImplementQueueUsingStacks {

    static class MyQueue {
        private Stack<Integer> frontStack;
        private Stack<Integer> backStack;

        public MyQueue() {
            this.frontStack = new Stack<>();
            this.backStack = new Stack<>();
        }

        // Push element to the back of queue
        public void push(int x) {
            frontStack.push(x);
        }

        // Remove and return the front element
        public int pop() {
            // Move elements to backStack to reverse order
            while (!frontStack.isEmpty()) {
                backStack.push(frontStack.pop());
            }

            int value = backStack.pop();

            // Move elements back to frontStack
            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }

            return value;
        }

        // Return the front element without removing it
        public int peek() {
            while (!frontStack.isEmpty()) {
                backStack.push(frontStack.pop());
            }

            int value = backStack.peek();

            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }

            return value;
        }

        // Return true if the queue is empty
        public boolean empty() {
            return frontStack.isEmpty() && backStack.isEmpty();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println("Peek: " + queue.peek());   // Output: 1
        System.out.println("Pop: " + queue.pop());     // Output: 1
        System.out.println("Empty: " + queue.empty()); // Output: false
    }
}
