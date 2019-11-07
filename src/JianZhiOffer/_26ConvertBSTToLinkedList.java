package JianZhiOffer;

public class _26ConvertBSTToLinkedList {
    public static void main(String[] args) {
        // a BST for test
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);

        //InOrderTraverse(root);
        TreeNode pNode = Convert(root);
        while(pNode != null){
            System.out.println(pNode.val);
            pNode = pNode.right;
        }
    }

    private static TreeNode head = null;
    private static TreeNode pLastNodeInList = null;

    public static TreeNode Convert(TreeNode root){
        InOrderTraverse(root);
        return head;
    }

    public static void InOrderTraverse(TreeNode root){
        if(root == null) return;
        InOrderTraverse(root.left);
        //System.out.println(root.val);
        if(head == null){
            head = root;
            pLastNodeInList = root;
        }else{
            pLastNodeInList.right = root;
            root.left = pLastNodeInList;
            pLastNodeInList = root;
        }
        InOrderTraverse(root.right);
    }
}
