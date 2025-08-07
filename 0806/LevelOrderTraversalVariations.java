import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);

        System.out.println("每層節點分層存儲：");
        List<List<Integer>> levels = levelOrder(root);
        for (List<Integer> level : levels) {
            System.out.println(level);
        }

        System.out.println("\n之字形層序走訪：");
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        for (List<Integer> level : zigzag) {
            System.out.println(level);
        }

        System.out.println("\n每層最後一個節點：");
        List<Integer> rightmost = rightmostPerLevel(root);
        System.out.println(rightmost);

        System.out.println("\n垂直層序走訪：");
        List<List<Integer>> vertical = verticalOrder(root);
        for (List<Integer> group : vertical) {
            System.out.println(group);
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                level.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            result.add(level);
        }
        return result;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        boolean leftToRight = true;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (leftToRight) level.addLast(cur.val);
                else level.addFirst(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    public static List<Integer> rightmostPerLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode last = null;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                last = cur;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            if (last != null) result.add(last.val);
        }
        return result;
    }

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();
        q.offer(root);
        colQ.offer(0);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            int col = colQ.poll();
            map.computeIfAbsent(col, x -> new ArrayList<>()).add(cur.val);
            if (cur.left != null) {
                q.offer(cur.left);
                colQ.offer(col - 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                colQ.offer(col + 1);
            }
        }
        result.addAll(map.values());
        return result;
    }
}
