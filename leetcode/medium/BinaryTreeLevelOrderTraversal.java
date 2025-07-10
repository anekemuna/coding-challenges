// https://leetcode.com/problems/binary-tree-level-order-traversal/
// Problem: Binary Tree Level Order Traversal
// Difficulty: Medium
// Tags: Tree, Breadth-First Search, Binary Tree

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

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

    // Solution 1: BFS (Iterative)
    // Time: O(n), Space: O(n)
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // base case
        if (root == null) return result;

        // initialize queue and add root to i
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);

        // while queue is not empty
        while (!queue.isEmpty()) {
            int size = queue.size(); // store number of nodes at level
            List<Integer> currentLevel = new ArrayList<>(); // temp list

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.removeFirst(); // dequeue
                currentLevel.add(node.val); // add node.val to list (currentLevel)

                // check left
                if (node.left != null) queue.addLast(node.left); // enqueue
                // check right
                if (node.right != null) queue.addLast(node.right); // enqueue
            }
            // add temp list (currentLevel to result)
            result.add(currentLevel);
        }

        return result;
    }

    // Solution 2: DFS (Recursive)
    // Time: O(n), Space: O(n)
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // base case
        if (root == null) {return result;}

        // call recursive function
        dfsHelper(root, 0, result);
        return result;
    }

    private void dfsHelper(TreeNode node, int level, List<List<Integer>> result) {
        // base case
        if (node == null) return;

        // using result.size() to keep track of level
        if (result.size() == level) {
            result.add(new ArrayList<>()); // add empty list for level
        }

        // add node value to the list at index level (.i.e get() returns List<Integer>)
        result.get(level).add(node.val);

        // call recursion on left subtree
        dfsHelper(node.left, level + 1, result);
        // call recursion on right subtree
        dfsHelper(node.right, level + 1, result);
    }

    // Sample Test
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        // Tree: [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3,
                            new TreeNode(9),
                            new TreeNode(20,
                                new TreeNode(15),
                                new TreeNode(7)));

        System.out.println("\nTree 1: [3,9,20,null,null,15,7]");
        System.out.println("Level Order (BFS): " + solution.levelOrderBFS(root));
        System.out.println("Level Order (DFS): " + solution.levelOrderDFS(root));

        // Tree: [3,9,20,14,null,15,7]
        TreeNode root2 = new TreeNode(3,
                            new TreeNode(9,
                                new TreeNode(14), 
                                null),
                            new TreeNode(20,
                                new TreeNode(15),
                                new TreeNode(7)));

        System.out.println("\nTree 2: [3,9,20,14,null,15,7]");
        System.out.println("Level Order (BFS): " + solution.levelOrderBFS(root2));
        System.out.println("Level Order (DFS): " + solution.levelOrderDFS(root2));
    }
}
