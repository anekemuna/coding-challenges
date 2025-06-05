// https://leetcode.com/problems/middle-of-the-linked-list/
// Problem: Middle of the Linked List
// Difficulty: Easy
// Tags: Linked List, Two Pointers


public class MiddleOfLinkedList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Solution 1: Count nodes then find midpoint (Two-pass)
    // Time: O(n), Space: O(1)
    public ListNode middleNodeTwoPass(ListNode head) {
        int count = 0;
        ListNode current = head;

        // Count total nodes
        while (current != null) {
            count++;
            current = current.next;
        }

        int mid = count / 2;

        // Move to the middle node
        current = head;
        for (int i = 0; i < mid; i++) {
            current = current.next;
        }

        return current;
    }

    // Solution 2: Tortoise and Hare / Fast & Slow Pointers (One-pass)
    // Time: O(n), Space: O(1)
    public ListNode middleNodeOnePass(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // move one step
            fast = fast.next.next;     // move two steps
        }

        return slow;
    }

    // Sample Test
    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);

        MiddleOfLinkedList solution = new MiddleOfLinkedList();

        System.out.println("Middle Node (Two Pass): " + solution.middleNodeTwoPass(head).val);  // Output: 3
        System.out.println("Middle Node (One Pass): " + solution.middleNodeOnePass(head).val);  // Output: 3
    }
}
