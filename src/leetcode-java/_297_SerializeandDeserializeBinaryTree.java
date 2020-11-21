package LeetCode;

import java.util.ArrayList;
import java.util.List;


// You may serialize the following tree:
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//as "[1,2,3,null,null,4,5]"
public class _297_SerializeandDeserializeBinaryTree {
    /**
     * 层次遍历法
     * 难点主要在于反序列化，这种做法值得借鉴
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        String lastVal = "";
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove(0);
            if(node != null) {
                sb.append(node.val);
                lastVal = String.valueOf(node.val);
                sb.append(",");
                queue.add(node.left);
                queue.add(node.right);
            }else{
                sb.append("null,");
            }
        }
        return sb.toString().substring(0, sb.toString().lastIndexOf(lastVal)+lastVal.length());
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("".equals(data)) return null;
        String[] segments = data.split(",");
        List<TreeNode> queue = new ArrayList<>();
        int i = 1;
        TreeNode root = new TreeNode(Integer.parseInt(segments[0]));
        TreeNode curr = root;
        queue.add(curr);
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove(0);
            if(i < segments.length && !segments[i].equals("null")) {
                curr = new TreeNode(Integer.parseInt(segments[i]));
                queue.add(curr);
                node.left = curr;
            }
            i++;
            if(i < segments.length && !segments[i].equals("null")) {
                curr = new TreeNode(Integer.parseInt(segments[i]));
                queue.add(curr);
                node.right = curr;
            }
            i++;
        }
        return root;
    }

    /**
     * 先序遍历法
     */

    public static void main(String[] args) {
        _297_SerializeandDeserializeBinaryTree obj = new _297_SerializeandDeserializeBinaryTree();
        TreeNode root = TreeNode.makeTree();
        String serialization = obj.serialize(root);
        System.out.println(serialization);
        root = obj.deserialize(serialization);
        TreeNode.preOrderTraversalWithRecursion(root);
    }
}
