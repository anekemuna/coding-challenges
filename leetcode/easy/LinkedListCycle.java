// https://leetcode.com/problems/linked-list-cycle/
// Problem: Linked List Cycle
// Difficulty: Easy
// Tag: Linked List, Two Pointers, HashSet
// Detect if a cycle exists in a linked list using Floyd’s Cycle Detection Algorithm or HashSet

import java.util.HashSet;

public class LinkedListCycle {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Solution 1: Floyd’s Cycle Detection (Tortoise and Hare)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean hasCycleFloyd(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    // Solution 2: HashSet to track visited nodes
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean hasCycleHashSet(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (visited.contains(current)) return true;
            visited.add(current);
            current = current.next;
        }

        return false;
    }

    // Main method for testing
    public static void main(String[] args) {
        LinkedListCycle solver = new LinkedListCycle();

        // Create nodes
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        // Setup cycle: 3 -> 2 -> 0 -> -4 -> (back to 2)
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // cycle here

        System.out.println("Cycle Detected (Floyd): " + solver.hasCycleFloyd(node1));
        System.out.println("Cycle Detected (HashSet): " + solver.hasCycleHashSet(node1));
    }
}
