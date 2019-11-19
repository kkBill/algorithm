package LeetCode;

import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode makeTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(6);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        return root;
    }

    // 迭代式前序遍历，该版本无法保留叶节点到根节点的路径信息
//    private static void preOrderTraversalWithIterative(TreeNode root){
//        if(root == null) return;
//        s.push(root);
//
//        while(!s.empty()){
//            TreeNode currNode = s.pop();
//            System.out.print(currNode.val + " ");
//
//            if(currNode.right != null) s.push(currNode.right);
//            if(currNode.left != null) s.push(currNode.left);
//        }
//    }

    // 迭代式前序遍历(v2)，该版本可以保留叶节点到根节点的路径信息

    public static void preOrderTraversalWithIteration(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> s = new Stack<>();
        while (p != null || !s.empty()) {
            if (p != null) {
                System.out.print(p.val + " ");
                s.push(p);
                p = p.left;
            } else {
                p = s.pop();
                p = p.right;
            }
        }
    }

    public static void preOrderTraversalWithRecursion(TreeNode root){
        if(root != null){
            System.out.print(root.val + " ");
            preOrderTraversalWithRecursion(root.left);
            preOrderTraversalWithRecursion(root.right);
        }
    }

    public static void inOrderTraversalWithIteration(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> s = new Stack<>();
        while(p != null || !s.empty()){
            if(p!=null){
                s.push(p);
                p = p.left;
            }else{
                p = s.pop();
                System.out.print(p.val + " ");
                p = p.right;
            }
        }
    }


    public static void inOrderTraversalWithRecursion(TreeNode root){
        if(root!=null){
            inOrderTraversalWithRecursion(root.left);
            System.out.print(root.val + " ");
            inOrderTraversalWithRecursion(root.right);
        }
    }

    // 关键
    public static void postOrderTraversalWithIteration(TreeNode root) {
        TreeNode p = root;
        TreeNode lastVisitNode = null;
        Stack<TreeNode> s = new Stack<>();
        while (p != null || !s.empty()) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                TreeNode tmp = s.peek();
                if (tmp.right != null && tmp.right != lastVisitNode) {
                    p = tmp.right;
                } else {
                    System.out.print(tmp.val + " ");
                    s.pop();
                    lastVisitNode = tmp;
                    p = null; // key!
                }
            }
        }
    }

    public static void postOrderTraversalWithRecursion(TreeNode root) {
        if (root != null) {
            postOrderTraversalWithRecursion(root.left);
            postOrderTraversalWithRecursion(root.right);
            System.out.print(root.val + " ");
        }
    }


    public static void main(String[] args) {
        TreeNode root = makeTree();
        preOrderTraversalWithIteration(root);
        System.out.println();
        preOrderTraversalWithRecursion(root);
        System.out.println();

        inOrderTraversalWithIteration(root);
        System.out.println();
        inOrderTraversalWithRecursion(root);
        System.out.println();

        postOrderTraversalWithIteration(root);
        System.out.println();
        postOrderTraversalWithRecursion(root);
        System.out.println();
    }
}