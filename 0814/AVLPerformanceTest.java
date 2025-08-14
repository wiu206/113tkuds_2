import java.util.*;

public class AVLPerformanceTest {

    // ===== AVL 節點類別 =====
    static class AVLNode {
        int data, height;
        AVLNode left, right;

        AVLNode(int data) {
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

    // ===== AVL Tree 實作 =====
    static class AVLTree {
        private AVLNode root;

        public void insert(int data) {
            root = insertRec(root, data);
        }

        private AVLNode insertRec(AVLNode node, int data) {
            if (node == null) return new AVLNode(data);

            if (data < node.data) node.left = insertRec(node.left, data);
            else if (data > node.data) node.right = insertRec(node.right, data);
            else return node; // 不插入重複值

            node.updateHeight();
            return rebalance(node, data);
        }

        private AVLNode rebalance(AVLNode node, int data) {
            int balance = node.getBalance();

            // LL
            if (balance > 1 && data < node.left.data) return rightRotate(node);
            // RR
            if (balance < -1 && data > node.right.data) return leftRotate(node);
            // LR
            if (balance > 1 && data > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // RL
            if (balance < -1 && data < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        private AVLNode rightRotate(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;
            x.right = y;
            y.left = T2;
            y.updateHeight();
            x.updateHeight();
            return x;
        }

        private AVLNode leftRotate(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;
            y.left = x;
            x.right = T2;
            x.updateHeight();
            y.updateHeight();
            return y;
        }

        public boolean search(int data) {
            return searchRec(root, data);
        }

        private boolean searchRec(AVLNode node, int data) {
            if (node == null) return false;
            if (data == node.data) return true;
            if (data < node.data) return searchRec(node.left, data);
            return searchRec(node.right, data);
        }
    }

    // ===== 效能測試 =====
    public static void main(String[] args) {
        System.out.println("=== AVL 樹性能測試 ===");

        AVLTree avlTree = new AVLTree();
        Random random = new Random();
        int n = 10000;

        // 插入測試
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            avlTree.insert(random.nextInt(100000));
        }
        long insertTime = System.nanoTime() - startTime;

        // 搜尋測試
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            avlTree.search(random.nextInt(100000));
        }
        long searchTime = System.nanoTime() - startTime;

        System.out.println("插入 " + n + " 個元素耗時: " +
                insertTime / 1_000_000 + " ms");
        System.out.println("搜尋 1000 次耗時: " +
                searchTime / 1_000_000 + " ms");
        System.out.println("平均插入時間複雜度: O(log n)");
        System.out.println("平均搜尋時間複雜度: O(log n)");
    }
}
