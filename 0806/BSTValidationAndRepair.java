import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode validBST = new TreeNode(10);
        validBST.left = new TreeNode(5);
        validBST.right = new TreeNode(15);
        System.out.println("是否為有效BST：" + isValidBST(validBST));

        TreeNode invalidBST = new TreeNode(10);
        invalidBST.left = new TreeNode(15);
        invalidBST.right = new TreeNode(5);
        List<TreeNode> invalids = findInvalidNodes(invalidBST);
        System.out.print("不符合BST規則的節點：");
        for (TreeNode node : invalids) System.out.print(node.val + " ");
        System.out.println();

        TreeNode corrupted = new TreeNode(10);
        corrupted.left = new TreeNode(5);
        corrupted.right = new TreeNode(8);
        System.out.println("修復前是否為BST：" + isValidBST(corrupted));
        recoverBST(corrupted);
        System.out.println("修復後是否為BST：" + isValidBST(corrupted));

        TreeNode toFix = new TreeNode(10);
        toFix.left = new TreeNode(15);
        toFix.right = new TreeNode(5);
        int removed = minRemovalsToBST(toFix);
        System.out.println("需移除節點數使其為BST：" + removed);
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static List<TreeNode> findInvalidNodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        findInvalid(root, Long.MIN_VALUE, Long.MAX_VALUE, result);
        return result;
    }

    public static void findInvalid(TreeNode node, long min, long max, List<TreeNode> result) {
        if (node == null) return;
        if (node.val <= min || node.val >= max) result.add(node);
        findInvalid(node.left, min, node.val, result);
        findInvalid(node.right, node.val, max, result);
    }

    static TreeNode first = null, second = null, prev = new TreeNode(Integer.MIN_VALUE);

    public static void recoverBST(TreeNode root) {
        first = second = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorderRecover(root);
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }

    public static void inorderRecover(TreeNode node) {
        if (node == null) return;
        inorderRecover(node.left);
        if (first == null && prev.val > node.val) first = prev;
        if (first != null && prev.val > node.val) second = node;
        prev = node;
        inorderRecover(node.right);
    }

    public static int minRemovalsToBST(TreeNode root) {
        return countNodes(root) - longestBST(root).size;
    }

    public static int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    static class BSTInfo {
        boolean isBST;
        int size;
        int min, max;
        BSTInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    public static BSTInfo longestBST(TreeNode node) {
        if (node == null) return new BSTInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        BSTInfo left = longestBST(node.left);
        BSTInfo right = longestBST(node.right);

        if (left.isBST && right.isBST && node.val > left.max && node.val < right.min) {
            int size = 1 + left.size + right.size;
            int min = node.left == null ? node.val : left.min;
            int max = node.right == null ? node.val : right.max;
            return new BSTInfo(true, size, min, max);
        }

        return new BSTInfo(false, Math.max(left.size, right.size), 0, 0);
    }
}
