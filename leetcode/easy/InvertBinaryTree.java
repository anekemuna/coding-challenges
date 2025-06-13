// https://leetcode.com/problems/invert-binary-tree/
// Problem: Invert Binary Tree
// Difficulty: Easy
// Tags: Tree, DFS, BFS, Recursion

import java.util.*;

public class InvertBinaryTree {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Recursive DFS solution
    public TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) return null;

        // recursively invert left and right sub trees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // swap left and right children
        root.left = right;
        root.right = left;

        return root;
    }

    // Print tree level-order for easy visualization
    public void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<String> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node != null) {
                    level.add(String.valueOf(node.val));
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    level.add("null");
                }
            }

            // Print current level
            System.out.println(String.join(" ", level));
        }
    }

    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();

        // LeetCode sample input: [4,2,7,1,3,6,9]
        TreeNode root = new TreeNode(4,
            new TreeNode(2, new TreeNode(1), new TreeNode(3)),
            new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );

        System.out.println("Original Tree:");
        solution.printTree(root);

        TreeNode inverted = solution.invertTree(root);

        System.out.println("\nInverted Tree:");
        solution.printTree(inverted);
    }
}
