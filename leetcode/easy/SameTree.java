// https://leetcode.com/problems/same-tree/
// Problem: Same Tree
// Difficulty: Easy
// Tags: Binary Tree, DFS



public class SameTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Recursive DFS
    // Time: O(n), Space: O(h)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;   // both null
        if (p == null || q == null) return false;  // only one null
        if (p.val != q.val) return false;          // values differ

        // recursively compare left & right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Sample Test
    public static void main(String[] args) {
        SameTree solution = new SameTree();

        // Tree 1:   1
        //          / \
        //         2   3
        TreeNode t1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        // Tree 2:   1
        //          / \
        //         2   3
        TreeNode t2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        // Tree 3:   1
        //          / 
        //         2   
        TreeNode t3 = new TreeNode(1, new TreeNode(2), null);

        System.out.println("Tree1 vs Tree2: " + solution.isSameTree(t1, t2)); // true
        System.out.println("Tree1 vs Tree3: " + solution.isSameTree(t1, t3)); // false
    }
}
