// https://leetcode.com/problems/balanced-binary-tree/
// Problem: Balanced Binary Tree
// Difficulty: Easy
// Tags: Tree, Depth-First Search, Binary Tree, Recursion

public class IsBalancedBinaryTree {

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

    // Solution: Bottom-up DFS (Post-order)
    // Time: O(n), Space: O(h) where h = height of tree
    public boolean isBalanced(TreeNode root) {
        // -1 signifies unbalanced
        return isBalancedHelper(root) != -1;
    }

    // Helper returns -1 if unbalanced, else height
    private int isBalancedHelper(TreeNode node) {
        
        // base case (leaf node child i.e null node)
        if (node == null) return 0;

        // recursive call on left subtree
        int left = isBalancedHelper(node.left);
        if (left == -1) return -1; // break whenever a subtree is unbalanced

        // recursive call on right subtree
        int right = isBalancedHelper(node.right);
        if (right == -1) return -1; // break wheneever a subtree is unbalanced

        if (Math.abs(left - right) > 1) return -1; // -1 signifies unbalanced

        // return the height of the node (i.e previous height + 1)
        return Math.max(left, right) + 1;
    }

    // Sample Test
    public static void main(String[] args) {
        IsBalancedBinaryTree solution = new IsBalancedBinaryTree();

        // Balanced tree: [1,2,3,4,5]
        TreeNode balanced = new TreeNode(1,
                                new TreeNode(2,
                                    new TreeNode(4),
                                    new TreeNode(5)),
                                new TreeNode(3));

        // Unbalanced tree: [1,2,null,3,null,4,null]
        TreeNode unbalanced = new TreeNode(1,
                                   new TreeNode(2,
                                       new TreeNode(3,
                                           new TreeNode(4), null),
                                       null),
                                   null);

        System.out.println("Balanced Tree: " + solution.isBalanced(balanced));     // true
        System.out.println("Unbalanced Tree: " + solution.isBalanced(unbalanced)); // false
    }
}
