// https://leetcode.com/problems/merge-two-sorted-lists/
// Problem: Merge Two Sorted Lists
// Difficulty: Easy
// Tag: Linked List
// Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

public class MergeTwoSortedLists {

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Iterative merge using dummy node
    // Time Complexity: O(n + m)
    // Space Complexity: O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listHead = new ListNode(); // dummy node (keeps pointer to head)
        ListNode curr = listHead;

        // Traverse both lists and merge them
        // Compare the values of the nodes in both lists
        // and attach the smaller one to the merged list
        // Move the pointer of the list from which the node was taken
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        // Attach the remaining nodes
        curr.next = (list1 != null) ? list1 : list2;

        return listHead.next;
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
        MergeTwoSortedLists solver = new MergeTwoSortedLists();

        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode merged = solver.mergeTwoLists(list1, list2);
        System.out.print("Merged List: ");
        printList(merged);
    }
}
