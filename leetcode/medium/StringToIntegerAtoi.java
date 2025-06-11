// https://leetcode.com/problems/string-to-integer-atoi/
// Problem: String to Integer (atoi)
// Difficulty: Medium
// Tags: String, Simulation

public class StringToIntegerAtoi {

    // Solution 1: Custom parser with flags
    // Time: O(n), Space: O(1)
    public int myAtoiFlagged(String s) {
        int result = 0, sign = 1;
        boolean signCheck = false, skippedWhitespace = false;

        for (char ch : s.toCharArray()) {
            // skip whitespace
            if (ch == ' ' && !skippedWhitespace) {
                continue;
            }

            // check sign
            if (ch == '-' && !signCheck) {
                sign = -1;
                signCheck = true;
                skippedWhitespace = true;
                continue;
            } else if (ch == '+' && !signCheck) {
                signCheck = true;
                skippedWhitespace = true;
                continue;
            }

            // check is non-digit character
            if (!Character.isDigit(ch)) {
                break;
            } else {
                signCheck = true;
                skippedWhitespace = true;
                int digit = ch - '0';

                // check for overflow
                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                result = (result * 10) + digit;
            }
        }

        return result * sign;
    }

    // Solution 2: Clean version with while loops and index
    // Time: O(n), Space: O(1)
    public int myAtoiClean(String s) {
        int i = 0, n = s.length(), sign = 1, result = 0;

        // 1. Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Check for optional sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Convert digits to integer
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // 4. Check for overflow before multiplying
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    // Sample Test
    public static void main(String[] args) {
        StringToIntegerAtoi solution = new StringToIntegerAtoi();

        System.out.println("Using Flagged Solution:");
        System.out.println(solution.myAtoiFlagged("42"));       // Output: 42
        System.out.println(solution.myAtoiFlagged("   -42"));   // Output: -42
        
        System.out.println("\nUsing Clean Solution:");
        System.out.println(solution.myAtoiClean("1337c0d3")); // Output: 1337
        System.out.println(solution.myAtoiClean("4193 with words")); // Output: 4193
    }
}
