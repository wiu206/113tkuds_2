import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {15, 10, 20, 8, 12, 17, 25};
        for (int v : values) {
            root = insert(root, v);
        }

        int min = 10, max = 20;
        List<Integer> rangeList = new ArrayList<>();
        rangeQuery(root, min, max, rangeList);
        System.out.println("範圍查詢 [" + min + ", " + max + "]：" + rangeList);
        System.out.println("範圍計數：" + countInRange(root, min, max));
        System.out.println("範圍總和：" + sumInRange(root, min, max));

        int target = 19;
        int closest = findClosest(root, target);
        System.out.println("最接近 " + target + " 的節點：" + closest);
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    public static void rangeQuery(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) rangeQuery(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) rangeQuery(node.right, min, max, result);
    }

    public static int countInRange(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return countInRange(node.right, min, max);
        if (node.val > max) return countInRange(node.left, min, max);
        return 1 + countInRange(node.left, min, max) + countInRange(node.right, min, max);
    }

    public static int sumInRange(TreeNode node, int min, int max) {
        if (node == null) return 0;
        if (node.val < min) return sumInRange(node.right, min, max);
        if (node.val > max) return sumInRange(node.left, min, max);
        return node.val + sumInRange(node.left, min, max) + sumInRange(node.right, min, max);
    }

    public static int findClosest(TreeNode node, int target) {
        int closest = node.val;
        while (node != null) {
            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            } else if (Math.abs(node.val - target) == Math.abs(closest - target)) {
                closest = Math.min(closest, node.val);
            }
            if (target < node.val) node = node.left;
            else if (target > node.val) node = node.right;
            else break;
        }
        return closest;
    }
}
