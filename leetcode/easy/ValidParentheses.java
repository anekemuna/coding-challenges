// https://leetcode.com/problems/valid-parentheses/
// Problem: Valid Parentheses
// Difficulty: Easy
// Tag: Stack, String

import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    // Solution 1: Basic Stack approach
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean isValidBasic(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (!stack.isEmpty()) {
                char last = stack.pop();
                if ((last == '(' && ch != ')') ||
                    (last == '{' && ch != '}') ||
                    (last == '[' && ch != ']')) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    // Solution 2: Using a Map for cleaner matching
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean isValidMap(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketMap = Map.of(
            ')', '(', 
            '}', '{', 
            ']', '['
        );

        for (char c : s.toCharArray()) {
            if (bracketMap.containsValue(c)) {
                stack.push(c);
            } else if (!stack.isEmpty() && stack.peek() == bracketMap.get(c)) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solver = new ValidParentheses();

        String input1 = "()[]{}";
        String input2 = "(]";
        String input3 = "([{}])";

        System.out.println("Basic: " + solver.isValidBasic(input1));  // true
        System.out.println("Basic: " + solver.isValidBasic(input2));  // false
        System.out.println("  Map: " + solver.isValidMap(input2));  // false
        System.out.println("  Map: " + solver.isValidMap(input3));      // true
    }
}
