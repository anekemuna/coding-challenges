// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// Problem: Serialize and Deserialize Binary Tree
// Difficulty: Hard
// Tags: Tree, String, Binary Tree, Design, Depth-First Search, Breadth-First Search


// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SerializeDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        serializeHelper(root, result);
        return result.toString();     
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        // base case: null root
        if (root == null) {
            sb.append("N,");
            return;
        }

        sb.append(root.val).append(",");

        // recursively serialize left and right subtrees
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] index = {0}; // mutable (i.e changeable) index passed by reference (using array to allow pass by reference)
        return deserializeHelper(arr, index);
    }

    private TreeNode deserializeHelper(String[] data, int[] index) {
        // base case: null i.e "N"
        if (data[index[0]].equals("N")) {
            index[0]++;
            return null;
        }

        // set root
        TreeNode root = new TreeNode(Integer.parseInt(data[index[0]]));
        index[0]++;

        // set left and right subtree recursively
        root.left = deserializeHelper(data, index);
        root.right = deserializeHelper(data, index);
        return root;
    }

    // Sample test for validation
    public static void main(String[] args) {
        SerializeDeserializeBinaryTree codec = new SerializeDeserializeBinaryTree();

        // Construct sample tree:     1
        //                           / \
        //                          2   3
        //                             / \
        //                            4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);

        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized Root: " + deserialized.val); // should print 1
    }
}
