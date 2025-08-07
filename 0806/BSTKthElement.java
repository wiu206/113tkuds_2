import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        int size;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) {
            root = insert(root, v);
        }

        System.out.println("第 3 小元素：" + kthSmallest(root, 3));
        System.out.println("第 2 大元素：" + kthLargest(root, 2));

        List<Integer> range = new ArrayList<>();
        kthRange(root, 2, 5, range);
        System.out.println("第 2 小到第 5 小元素：" + range);

        root = insert(root, 17);
        root = delete(root, 10);
        System.out.println("動態更新後第 4 小元素：" + kthSmallest(root, 4));
    }

    public static TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public static TreeNode delete(TreeNode node, int val) {
        if (node == null) return null;
        if (val < node.val) node.left = delete(node.left, val);
        else if (val > node.val) node.right = delete(node.right, val);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode minNode = getMin(node.right);
            node.val = minNode.val;
            node.right = delete(node.right, minNode.val);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public static TreeNode getMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public static int kthSmallest(TreeNode node, int k) {
        int leftSize = size(node.left);
        if (k <= leftSize) return kthSmallest(node.left, k);
        else if (k == leftSize + 1) return node.val;
        else return kthSmallest(node.right, k - leftSize - 1);
    }

    public static int kthLargest(TreeNode node, int k) {
        int rightSize = size(node.right);
        if (k <= rightSize) return kthLargest(node.right, k);
        else if (k == rightSize + 1) return node.val;
        else return kthLargest(node.left, k - rightSize - 1);
    }

    public static void kthRange(TreeNode node, int k, int j, List<Integer> result) {
        inOrderRange(node, new int[]{0}, k, j, result);
    }

    public static void inOrderRange(TreeNode node, int[] count, int k, int j, List<Integer> result) {
        if (node == null) return;
        inOrderRange(node.left, count, k, j, result);
        count[0]++;
        if (count[0] >= k && count[0] <= j) result.add(node.val);
        if (count[0] > j) return;
        inOrderRange(node.right, count, k, j, result);
    }

    public static int size(TreeNode node) {
        return node == null ? 0 : node.size;
    }
}
