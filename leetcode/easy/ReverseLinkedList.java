// https://leetcode.com/problems/reverse-linked-list/
// Problem: Reverse Linked List
// Difficulty: Easy
// Tag: Linked List, Recursion
// Reverse a singly linked list recursively.

public class ReverseLinkedList {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Recursive solution
    // Time Complexity: O(n)
    // Space Complexity: O(n) due to recursion stack
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    // Helper method to print list
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + (node.next != null ? " -> " : ""));
            node = node.next;
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        ReverseLinkedList solver = new ReverseLinkedList();

        ListNode list = new ListNode(1,
                          new ListNode(2,
                          new ListNode(3,
                          new ListNode(4,
                          new ListNode(5)))));

        System.out.print("Original List: ");
        printList(list);

        ListNode reversed = solver.reverseList(list);
        System.out.print("Reversed List: ");
        printList(reversed);
    }
}
