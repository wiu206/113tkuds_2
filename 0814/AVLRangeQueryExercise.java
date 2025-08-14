import java.util.ArrayList;
import java.util.List;

public class AVLRangeQueryExercise {

    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.height = 1;
        }

        void updateHeight() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            height = Math.max(lh, rh) + 1;
        }

        int getBalance() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            return lh - rh;
        }
    }

    private Node root;

    // 插入節點（自動平衡）
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data) node.left = insertRec(node.left, data);
        else if (data > node.data) node.right = insertRec(node.right, data);
        else return node;

        node.updateHeight();
        return rebalance(node, data);
    }

    // 平衡調整
    private Node rebalance(Node node, int data) {
        int balance = node.getBalance();

        if (balance > 1 && data < node.left.data) return rightRotate(node);
        if (balance < -1 && data > node.right.data) return leftRotate(node);
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 範圍查詢
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;

        // 若當前節點值大於最小值，先搜尋左子樹
        if (node.data > min) {
            rangeQueryRec(node.left, min, max, result);
        }

        // 若節點值在範圍內，加入結果
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        // 若當前節點值小於最大值，搜尋右子樹
        if (node.data < max) {
            rangeQueryRec(node.right, min, max, result);
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] nums = {20, 10, 30, 5, 15, 25, 35};
        for (int n : nums) tree.insert(n);

        System.out.println("查詢範圍 10 到 30:");
        List<Integer> result = tree.rangeQuery(10, 30);
        System.out.println(result);

        System.out.println("查詢範圍 5 到 15:");
        System.out.println(tree.rangeQuery(5, 15));

        System.out.println("查詢範圍 28 到 40:");
        System.out.println(tree.rangeQuery(28, 40));
    }
}
