// https://leetcode.com/problems/add-binary/
// Problem: Add Binary
// Difficulty: Easy
// Tags: Math, String, Simulation, Bit Manipulation

public class AddBinary {

    // Solution 1: Manual Bitwise Handling
    // Time: O(max(n, m)), Space: O(max(n, m))
    public String addBinaryManual(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int n = Math.min(aLength, bLength);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean overflow = false;

        // using the min length, process common length
        while (i < n) {
            char aChar = a.charAt(aLength - 1 - i);
            char bChar = b.charAt(bLength - 1 - i);
            if (!overflow) {
                if ((aChar == '0' && bChar == '1') || (aChar == '1' && bChar == '0')) {
                    sb.append("1");
                } else if (aChar == '1' && bChar == '1') {
                    sb.append("0");
                    overflow = true;
                } else { // both = 0 and no overflow
                    sb.append("0");
                }
            } else {
                if ((aChar == '0' && bChar == '1') || (aChar == '1' && bChar == '0')) {
                    sb.append("0");
                } else if (aChar == '1' && bChar == '1') {
                    sb.append("1");
                } else { // both = 0 and overflows
                    sb.append("1");
                    overflow = false;
                }
            }
            i++;
        }

        // processing remaining digits of a
        while (i < aLength) {
            char aChar = a.charAt(aLength - 1 - i);
            if (!overflow) {
                sb.append(aChar);
            } else {
                if (aChar == '1') {
                    sb.append("0");
                } else {
                    sb.append("1");
                    overflow = false;
                }
            }
            i++;
        }

        // processing remaining digits of b
        while (i < bLength) {
            char bChar = b.charAt(bLength - 1 - i);
            if (!overflow) {
                sb.append(bChar);
            } else {
                if (bChar == '1') {
                    sb.append("0");
                } else {
                    sb.append("1");
                    overflow = false;
                }
            }
            i++;
        }

        // Final carry (i.e. still overflow)
        if (overflow) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    // Solution 2: Reverse Strings and Simulate Addition
    // Time: O(max(n, m)), Space: O(max(n, m))
    public String addBinaryReversed(String a, String b) {
        StringBuilder result = new StringBuilder();

        // convert strings to stringbuilder and reverse
        StringBuilder aSb = new StringBuilder(a).reverse();
        StringBuilder bSb = new StringBuilder(b).reverse();

        int aLen = a.length(), bLen = b.length();
        int n = Math.max(aLen, bLen),  // loop based on longest length
        carry = 0;

        for (int i = 0; i < n; i++) {

            // get digit at i, if i out of bound set to 0
            int aDigit = i < aLen ? aSb.charAt(i) - '0' : 0;
            int bDigit = i < bLen ? bSb.charAt(i) - '0' : 0;

            // calculate sum
            int total = aDigit + bDigit + carry;

            // value to append (e.g. "1" + "1" = "10", there digitToAppend = 0, carry = 1)
            result.append(total % 2);
            carry = total / 2;
        }

        // if there's overflow = add 1
        if (carry != 0) result.append("1");

        // reverse to get the actual string
        return result.reverse().toString();
    }

    // Solution 3: In-Place Addition (Optimal)
    // Time: O(max(n, m)), Space: O(max(n, m))
    public String addBinaryOptimal(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aLen = a.length() - 1, bLen = b.length() - 1, carry = 0;

        while (aLen >= 0 || bLen >= 0 || carry > 0) {
              // sum the values (carry + digitA + digitB)
            int sum = carry;
            // "a.charAt(aLen--) - '0'" -> char to integer conversion
            if (aLen >= 0) sum += a.charAt(aLen--) - '0'; // make sure not out of bounds
            if (bLen >= 0) sum += b.charAt(bLen--) - '0'; // make sure not out of bounds

            // value to append (e.g. "1" + "1" = "10", there digitToAppend = 0, carry = 1)
            sb.append(sum % 2);
            carry = sum / 2; 
        }

        return sb.reverse().toString();
    }

    // Sample Test
    public static void main(String[] args) {
        AddBinary solution = new AddBinary();

        System.out.println("Manual: " + solution.addBinaryManual("1010", "1011"));   // Output: 10101
        System.out.println("Reversed: " + solution.addBinaryReversed("1010", "1011")); // Output: 10101
        System.out.println("Optimal: " + solution.addBinaryOptimal("1010", "1011"));   // Output: 10101
    }
}
