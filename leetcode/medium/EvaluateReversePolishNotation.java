// https://leetcode.com/problems/evaluate-reverse-polish-notation/
// Problem: Evaluate Reverse Polish Notation
// Difficulty: Medium
// Tag: Stack, Array

import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class EvaluateReversePolishNotation {

    // Solution 1: Stack with inline operator handling
    static class Solution1 {
        public int evalRPN(String[] tokens) {
            Stack<Integer> stack = new Stack<>();  // Stack to hold intermediate results

            for (String token : tokens) {
                switch (token) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        int second = stack.pop();
                        int first = stack.pop();
                        stack.push(first - second);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        second = stack.pop();
                        first = stack.pop();
                        stack.push(first / second);
                        break;
                    default:
                        stack.push(Integer.parseInt(token));  // Push number
                        break;
                }
            }

            return stack.pop();  // Final result
        }
    }

    // Solution 2: Modular with helper methods using ArrayDeque
    static class Solution2 {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new ArrayDeque<>();

            for (String token : tokens) {
                if (isOperator(token)) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(applyOperator(a, b, token));
                } else {
                    stack.push(Integer.parseInt(token));
                }
            }

            return stack.pop();
        }

        private boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || 
                   token.equals("*") || token.equals("/");
        }

        private int applyOperator(int a, int b, String op) {
            switch (op) {
                case "+": return a + b;
                case "-": return a - b;
                case "*": return a * b;
                case "/": return a / b;
                default: throw new IllegalArgumentException("Invalid operator: " + op);
            }
        }
    }

    // Optional test run
    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};  // ((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = 22

        System.out.println("Solution 1 Result: " + new Solution1().evalRPN(tokens));
        System.out.println("Solution 2 Result: " + new Solution2().evalRPN(tokens));
    }
}
