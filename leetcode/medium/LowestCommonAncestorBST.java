// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// Problem: Lowest Common Ancestor of a Binary Search Tree
// Difficulty: Medium
// Tags: Binary Tree, BST

public class LowestCommonAncestorBST {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // ------------------------------------------------------------------
    // Solution 1: General Binary Tree LCA (works for any tree, not just BST)
    // ------------------------------------------------------------------
    // Time: O(N), Space: O(H)
    public TreeNode lowestCommonAncestorGeneral(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestorGeneral(root.left, p, q);
        TreeNode right = lowestCommonAncestorGeneral(root.right, p, q);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    // ------------------------------------------------------------------
    // Solution 2: Optimal using BST properties
    // ------------------------------------------------------------------
    // Time: O(H), Space: O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse until we find the split point
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // split point or equal → root is the LCA
        return root;
    }

    // ------------------------------------------------------------------
    // Sample Test
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        LowestCommonAncestorBST solution = new LowestCommonAncestorBST();

        /*
                  6
                /   \
               2     8
              / \   / \
             0   4 7   9
                / \
               3   5
        */

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left;       // 2
        TreeNode q = root.left.right; // 4
        TreeNode q2 = root.right; // 8


        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA of " + p.val + " and " + q.val + " = " + lca.val); // Expected 2

        TreeNode lca2 = solution.lowestCommonAncestorGeneral(root, p, q2);
        System.out.println("LCA of " + p.val + " and " + q2.val + " = " + lca2.val); // Expected 6
    }
}
