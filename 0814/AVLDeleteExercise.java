public class AVLDeleteExercise {

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

    // 插入（方便測試用）
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

    // 刪除節點
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, int data) {
        if (node == null) return null;

        if (data < node.data) {
            node.left = deleteRec(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRec(node.right, data);
        } else {
            // 情況1: 葉子
            if (node.left == null && node.right == null) {
                return null;
            }
            // 情況2: 只有一個子節點
            else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // 情況3: 兩個子節點
            else {
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = deleteRec(node.right, successor.data);
            }
        }

        node.updateHeight();
        return rebalanceAfterDelete(node);
    }

    // 找最小值節點
    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 重新平衡（插入用）
    private Node rebalance(Node node, int data) {
        int balance = node.getBalance();

        // LL
        if (balance > 1 && data < node.left.data) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && data > node.right.data) {
            return leftRotate(node);
        }
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

    // 重新平衡（刪除用）
    private Node rebalanceAfterDelete(Node node) {
        int balance = node.getBalance();

        // LL
        if (balance > 1 && node.left.getBalance() >= 0) {
            return rightRotate(node);
        }
        // LR
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && node.right.getBalance() <= 0) {
            return leftRotate(node);
        }
        // RL
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 中序列印
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        int[] nums = {30, 20, 40, 10, 25, 35, 50};
        for (int n : nums) tree.insert(n);

        System.out.println("原始樹 (中序):");
        tree.inorder();

        System.out.println("刪除葉子節點 10:");
        tree.delete(10);
        tree.inorder();

        System.out.println("刪除只有一個子節點的節點 50:");
        tree.delete(50);
        tree.inorder();

        System.out.println("刪除有兩個子節點的節點 30:");
        tree.delete(30);
        tree.inorder();
    }
}
