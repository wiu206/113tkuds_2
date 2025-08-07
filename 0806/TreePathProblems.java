import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(25);

        System.out.println("根到葉節點的所有路徑：");
        List<List<Integer>> paths = new ArrayList<>();
        findAllPaths(root, new ArrayList<>(), paths);
        for (List<Integer> path : paths) System.out.println(path);

        int targetSum = 22;
        System.out.println("是否存在總和為 " + targetSum + " 的根到葉路徑：" + hasPathSum(root, targetSum));

        List<Integer> maxPath = new ArrayList<>();
        maxRootToLeaf(root, new ArrayList<>(), 0, new int[]{Integer.MIN_VALUE}, maxPath);
        System.out.println("最大總和根到葉路徑：" + maxPath);

        System.out.println("樹中最大路徑總和（直徑）：");
        maxPathSum = Integer.MIN_VALUE;
        findMaxPathSum(root);
        System.out.println(maxPathSum);
    }

    public static void findAllPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            findAllPaths(node.left, path, result);
            findAllPaths(node.right, path, result);
        }
        path.remove(path.size() - 1);
    }

    public static boolean hasPathSum(TreeNode node, int target) {
        if (node == null) return false;
        if (node.left == null && node.right == null) return node.val == target;
        return hasPathSum(node.left, target - node.val) || hasPathSum(node.right, target - node.val);
    }

    public static void maxRootToLeaf(TreeNode node, List<Integer> path, int sum, int[] maxSum, List<Integer> result) {
        if (node == null) return;
        path.add(node.val);
        sum += node.val;
        if (node.left == null && node.right == null) {
            if (sum > maxSum[0]) {
                maxSum[0] = sum;
                result.clear();
                result.addAll(path);
            }
        } else {
            maxRootToLeaf(node.left, path, sum, maxSum, result);
            maxRootToLeaf(node.right, path, sum, maxSum, result);
        }
        path.remove(path.size() - 1);
    }

    static int maxPathSum = Integer.MIN_VALUE;

    public static int findMaxPathSum(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, findMaxPathSum(node.left));
        int right = Math.max(0, findMaxPathSum(node.right));
        maxPathSum = Math.max(maxPathSum, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
