// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// Problem: Construct Binary Tree from Preorder and Inorder Traversal
// Difficulty: Medium
// Tags: Tree, Array, Hash Table, Divide and Conquer, Binary Tree

import java.util.*;

public class ConstructBinaryTreePreIn {

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

    // Solution 1: DFS with Linear Search
    // Time: O(n^2), Space: O(n)
    public TreeNode buildTreeLinear(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 
        0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int preEnd,
                                     int inStart, int inEnd) {
        // base case: empty 
        if (preStart > preEnd || inStart > inEnd) return null;
        
        // base case: 1 element /node
        TreeNode root = new TreeNode(preorder[preStart]); // create root
        if (preStart == preEnd) return root;

        // find the root's index in inorder array
        int rootIndex = inStart;
        while (rootIndex <= inEnd && inorder[rootIndex] != root.val) {
            rootIndex++;
        }

        int leftTreeSize = rootIndex - inStart;

        // use recursion to build left and right subtrees
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, preStart + leftTreeSize,
                                    inStart, rootIndex - 1);

        root.right = buildTreeHelper(preorder, inorder, preStart + leftTreeSize + 1, preEnd,
                                     rootIndex + 1, inEnd);

        return root;
    }

    // Solution 2: DFS with HashMap (Optimized)
    // Solution gotten from Neetcode
    // Time: O(n), Space: O(n)

    int preorderIndex = 0;
    Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTreeOptimized(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int mid = inorderIndexMap.get(rootVal);

        root.left = build(preorder, left, mid - 1);
        root.right = build(preorder, mid + 1, right);

        return root;
    }

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

    // Sample Test
    public static void main(String[] args) {
        ConstructBinaryTreePreIn solution = new ConstructBinaryTreePreIn();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode result = solution.buildTreeOptimized(preorder, inorder);
        System.out.println("Tree built successfully (root value): " + result.val); // Output: 3
        solution.printTree(result);
    }
}
