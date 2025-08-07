import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(20);

        int sum = sumNodes(root);
        int count = countNodes(root);
        System.out.println("節點總和：" + sum);
        System.out.println("節點平均：" + ((double) sum / count));

        int max = findMax(root);
        int min = findMin(root);
        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);

        int width = treeWidth(root);
        System.out.println("樹的最大寬度：" + width);

        boolean isComplete = isCompleteBinaryTree(root);
        System.out.println("是否為完全二元樹：" + isComplete);
    }

    public static int sumNodes(TreeNode node) {
        if (node == null) return 0;
        return node.val + sumNodes(node.left) + sumNodes(node.right);
    }

    public static int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static int findMax(TreeNode node) {
        if (node == null) return Integer.MIN_VALUE;
        return Math.max(node.val, Math.max(findMax(node.left), findMax(node.right)));
    }

    public static int findMin(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        return Math.min(node.val, Math.min(findMin(node.left), findMin(node.right)));
    }

    public static int treeWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean seenNull = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                seenNull = true;
            } else {
                if (seenNull) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
}
