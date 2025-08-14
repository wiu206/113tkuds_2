import java.util.*;

public class PersistentAVLExercise {

    // 節點類別（不可變）
    static class Node {
        final int data;
        final Node left, right;
        final int height;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = Math.max(getHeight(left), getHeight(right)) + 1;
        }

        static int getHeight(Node node) {
            return (node == null) ? 0 : node.height;
        }

        int getBalance() {
            return getHeight(left) - getHeight(right);
        }
    }

    // 每個版本的根節點
    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); // 版本 0 = 空樹
    }

    // 插入操作（會回傳新版本索引）
    public int insert(int versionIndex, int data) {
        Node newRoot = insertRec(versions.get(versionIndex), data);
        versions.add(newRoot);
        return versions.size() - 1;
    }

    private Node insertRec(Node node, int data) {
        if (node == null) return new Node(data, null, null);

        if (data < node.data) {
            Node newLeft = insertRec(node.left, data);
            node = new Node(node.data, newLeft, node.right);
        } else if (data > node.data) {
            Node newRight = insertRec(node.right, data);
            node = new Node(node.data, node.left, newRight);
        } else {
            return node; // 相等不插入
        }

        return rebalance(node);
    }

    // 旋轉與平衡
    private Node rebalance(Node node) {
        int balance = node.getBalance();

        // LL
        if (balance > 1 && node.left != null && node.left.getBalance() >= 0) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && node.right != null && node.right.getBalance() <= 0) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && node.left != null && node.left.getBalance() < 0) {
            Node newLeft = leftRotate(node.left);
            node = new Node(node.data, newLeft, node.right);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && node.right != null && node.right.getBalance() > 0) {
            Node newRight = rightRotate(node.right);
            node = new Node(node.data, node.left, newRight);
            return leftRotate(node);
        }
        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = (x != null) ? x.right : null;
        return new Node(x.data, x.left, new Node(y.data, T2, y.right));
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = (y != null) ? y.left : null;
        return new Node(y.data, new Node(x.data, x.left, T2), y.right);
    }

    // 查詢版本（中序列印）
    public void printVersion(int versionIndex) {
        Node root = versions.get(versionIndex);
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    // 獲取版本總數
    public int getVersionCount() {
        return versions.size();
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        int v1 = tree.insert(0, 10);
        int v2 = tree.insert(v1, 20);
        int v3 = tree.insert(v2, 5);
        int v4 = tree.insert(v3, 15);

        System.out.println("版本 0: ");
        tree.printVersion(0);

        System.out.println("版本 1: ");
        tree.printVersion(v1);

        System.out.println("版本 2: ");
        tree.printVersion(v2);

        System.out.println("版本 3: ");
        tree.printVersion(v3);

        System.out.println("版本 4: ");
        tree.printVersion(v4);

        System.out.println("總版本數: " + tree.getVersionCount());
    }
}
