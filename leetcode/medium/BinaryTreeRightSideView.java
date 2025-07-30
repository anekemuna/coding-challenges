// https://leetcode.com/problems/binary-tree-right-side-view/
// Problem: Binary Tree Right Side View
// Difficulty: Medium
// Tags: Tree, Depth-First Search, Breadth-First Search, Binary Tree

import java.util.*;

public class BinaryTreeRightSideView {

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

    // Solution 1: BFS (Level-order traversal)
    // Time: O(n), Space: O(n)
    public List<Integer> rightSideViewBFS(TreeNode root) {
        // queue for tracking nodes
        Deque<TreeNode> queue = new ArrayDeque<>();
        // list for result
        List<Integer> result = new ArrayList<>();
        // base case: empty tree
        if (root == null) return result;
        
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int length = queue.size(); // size at level x
            TreeNode lastNode = null; // rightmost node after the for loop

            for (int i = 0; i < length; i++) {
                lastNode = queue.removeFirst();
                // add children to queue
                if (lastNode.left != null) queue.addLast(lastNode.left);
                if (lastNode.right != null) queue.addLast(lastNode.right);
            }

            if (lastNode != null) result.add(lastNode.val);
        }
        // lastNode at current level
        return result;
    }

    // Solution 2: DFS (Right-first traversal)
    // Time: O(n), Space: O(h)
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        dfsHelper(root, 0, result);
        return result;
    }

    private void dfsHelper(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;

        // First time visiting
        if (depth == result.size()) {
            result.add(node.val);
        }

        dfsHelper(node.right, depth + 1, result);
        dfsHelper(node.left, depth + 1, result);
    }

    // Sample Test
    public static void main(String[] args) {
        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

        TreeNode root = new TreeNode(1,
                            new TreeNode(2, null, new TreeNode(5)),
                            new TreeNode(3, null, new TreeNode(4))
                        );

        System.out.println("Right Side View (BFS): " + solution.rightSideViewBFS(root)); // Output: [1, 3, 4]
        System.out.println("Right Side View (DFS): " + solution.rightSideViewDFS(root)); // Output: [1, 3, 4]
    }
}
