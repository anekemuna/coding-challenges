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

    // Solution 1: Iterative Approach
    // Time: O(n), Space: O(1)
    public ListNode reverseIterative(ListNode head) {
        ListNode prev = null, current = head;

        while (current != null) {
            ListNode next = current.next; // Store next node
            current.next = prev;         // Reverse the pointer
            prev = current;              // Move prev forward
            current = next;              // Move current forward
        }

        return prev; // New head
    }


    // Solution 2: Recursive Approach
    // Time Complexity: O(n)
    // Space Complexity: O(n) due to recursion stack
    public ListNode reverseRecursive(ListNode head) {
        // Base case: empty or single-node list
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseRecursive(head.next); // Reverse rest
        head.next.next = head;  // Reverse current node
        head.next = null;       // Break the link

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

        
        ListNode reversedIter = solver.reverseIterative(list);
        System.out.print("Reversed List (Iterative): ");
        printList(reversedIter);

        // Rebuild list since it was reversed
        list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

        ListNode reversedRecur = solver.reverseRecursive(list);
        System.out.print("Reversed List (Recursive): ");
        printList(reversedRecur);
    }
}
