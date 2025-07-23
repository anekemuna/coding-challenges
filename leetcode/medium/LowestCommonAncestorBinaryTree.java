// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// Problem: Lowest Common Ancestor of a Binary Tree
// Difficulty: Medium
// Tags: Tree, Depth-First Search, Binary Tree

public class LowestCommonAncestorBinaryTree {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Solution: Recursive DFS
    // Time: O(n), Space: O(h)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if current node is null or matches one of the targets
        if (root == null || root == p || root == q) return root;

        // Recurse on left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides return non-null, this node is the LCA
        if (left != null && right != null) return root;

        // Otherwise return non-null child
        return (left != null) ? left : right;
    }

    // Sample Test
    public static void main(String[] args) {
        LowestCommonAncestorBinaryTree solution = new LowestCommonAncestorBinaryTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left; // Node 5
        TreeNode q = root.left.right.right; // Node 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor: " + (lca != null ? lca.val : "null"));  // Output: 5
    }
}
