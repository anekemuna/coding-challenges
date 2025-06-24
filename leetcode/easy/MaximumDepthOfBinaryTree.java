// https://leetcode.com/problems/maximum-depth-of-binary-tree/
// Problem: Maximum Depth of Binary Tree
// Difficulty: Easy
// Tags: Tree, Depth-First Search, Binary Tree

public class MaximumDepthOfBinaryTree {

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

    // Solution: DFS to find max depth
    // Time: O(n), Space: O(h)
    public int maxDepth(TreeNode root) {
        return maxDepthHelper(root);
    }

    private int maxDepthHelper(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepthHelper(root.left);
        int right = maxDepthHelper(root.right);

        return 1 + Math.max(left, right);
    }

    // Sample Test
    public static void main(String[] args) {
        MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();

        // Tree: [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3,
                            new TreeNode(9),
                            new TreeNode(20,
                                new TreeNode(15),
                                new TreeNode(7)));

        System.out.println("Max Depth: " + solution.maxDepth(root));  // Output: 3
    }
}
