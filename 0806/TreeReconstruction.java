import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder =  {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        TreeNode fromPreIn = buildTreePreIn(preorder, inorder);
        System.out.print("前序+中序重建的中序結果：");
        printInorder(fromPreIn);
        System.out.println();

        TreeNode fromPostIn = buildTreePostIn(postorder, inorder);
        System.out.print("後序+中序重建的中序結果：");
        printInorder(fromPostIn);
        System.out.println();

        TreeNode fromLevel = buildCompleteTree(levelOrder);
        System.out.print("層序重建完全樹的中序結果：");
        printInorder(fromLevel);
        System.out.println();
    }

    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return helperPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public static TreeNode helperPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> map) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int inRoot = map.get(pre[ps]);
        int leftSize = inRoot - is;
        root.left = helperPreIn(pre, ps + 1, ps + leftSize, in, is, inRoot - 1, map);
        root.right = helperPreIn(pre, ps + leftSize + 1, pe, in, inRoot + 1, ie, map);
        return root;
    }

    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return helperPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public static TreeNode helperPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> map) {
        if (ps > pe || is > ie) return null;
        TreeNode root = new TreeNode(post[pe]);
        int inRoot = map.get(post[pe]);
        int leftSize = inRoot - is;
        root.left = helperPostIn(post, ps, ps + leftSize - 1, in, is, inRoot - 1, map);
        root.right = helperPostIn(post, ps + leftSize, pe - 1, in, inRoot + 1, ie, map);
        return root;
    }

    public static TreeNode buildCompleteTree(int[] levelOrder) {
        if (levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < levelOrder.length) {
            TreeNode node = q.poll();
            if (i < levelOrder.length) {
                node.left = new TreeNode(levelOrder[i++]);
                q.offer(node.left);
            }
            if (i < levelOrder.length) {
                node.right = new TreeNode(levelOrder[i++]);
                q.offer(node.right);
            }
        }
        return root;
    }

    public static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
