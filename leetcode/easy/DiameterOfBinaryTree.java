// https://leetcode.com/problems/diameter-of-binary-tree/
// Problem: Diameter of Binary Tree
// Difficulty: Easy
// Tags: Tree, Depth-First Search, Binary Tree

public class DiameterOfBinaryTree {

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

    // Solution: Post-order DFS to track the longest path
    // Time: O(n), Space: O(h)
    int diameter = 0; // Global variable to store max diameter

    public int diameterOfBinaryTree(TreeNode root) {
        diameterRecur(root);
        return diameter;
    }

    private int diameterRecur(TreeNode node) {
        if (node == null) return 0;

        int left = diameterRecur(node.left);
        int right = diameterRecur(node.right);

        // Update the diameter at this node
        diameter = Math.max(diameter, left + right);

        // return height
        return 1 + Math.max(left, right);
    }

    // Sample Test
    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

        // Tree: [1,2,3,4,5]
        TreeNode root = new TreeNode(1,
                            new TreeNode(2,
                                new TreeNode(4),
                                new TreeNode(5)),
                            new TreeNode(3));

        System.out.println("Diameter: " + solution.diameterOfBinaryTree(root));  // Output: 3
    }
}
