// https://leetcode.com/problems/basic-calculator/
// Problem: Basic Calculator
// Difficulty: Medium
// Tag: Stack, Recursion, String Parsing

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {

    // Solution 1: Iterative with explicit stack for signs and results
    // Time: O(n), Space: O(n)
    public int calculateIterative(String s) {
        int result = 0, sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;

        while (i < s.length()) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                result += sign * num;
                continue; // skip increment to avoid skipping next char
            }

            switch (ch) {
                case '+':
                    sign = 1;
                    break;
                case '-':
                    sign = -1;
                    break;
                case '(':
                    // Push current result and sign for the new sub-expression
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                    break;
                case ')':
                    // Pop sign and previous result, combine with current result
                    int prevSign = stack.pop();
                    int prevResult = stack.pop();
                    result = prevResult + prevSign * result;
                    break;
                case ' ':
                    // Ignore spaces
                    break;
            }
            i++;
        }
        return result;
    }

    // Solution 2: Recursive helper method to handle nested parentheses elegantly
    // Time: O(n), Space: O(h), h = max depth of nested parentheses
    private int i = 0;
    public int calculateRecursive(String s) {
        i = 0; // reset index before parsing
        return helper(s);
    }

    private int helper(String s) {
        int result = 0, num = 0, sign = 1;

        while (i < s.length()) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                result += sign * num;
                continue;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                i++;
                result += sign * helper(s); // recurse inside parentheses
            } else if (ch == ')') {
                return result; // end of recursion block
            }
            i++;
        }
        return result;
    }

    // Sample tests
    public static void main(String[] args) {
        BasicCalculator solution = new BasicCalculator();

        String test1 = "1 + 1";
        String test2 = " 2-1 + 2 ";
        String test3 = "(1+(4+5+2)-3)+(6+8)";

        System.out.println("Iterative:");
        System.out.println("Input: \"" + test1 + "\" Result: " + solution.calculateIterative(test1)); // 2
        System.out.println("Input: \"" + test2 + "\" Result: " + solution.calculateIterative(test2)); // 3
        System.out.println("Input: \"" + test3 + "\" Result: " + solution.calculateIterative(test3)); // 23

        System.out.println("\nRecursive:");
        System.out.println("Input: \"" + test1 + "\" Result: " + solution.calculateRecursive(test1)); // 2
        System.out.println("Input: \"" + test2 + "\" Result: " + solution.calculateRecursive(test2)); // 3
        System.out.println("Input: \"" + test3 + "\" Result: " + solution.calculateRecursive(test3)); // 23
    }
}
