public class AVLRotationExercise {

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
    }

    private static int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // 右旋
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // 左右旋
    public static Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 右左旋
    public static Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // 中序列印
    public static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + "(" + node.height + ") ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        // 測試 Left Left 情況 -> 右旋
        Node rootLL = new Node(30);
        rootLL.left = new Node(20);
        rootLL.left.left = new Node(10);
        rootLL.updateHeight();
        rootLL.left.updateHeight();
        System.out.println("LL 原始:");
        inorder(rootLL);
        rootLL = rightRotate(rootLL);
        System.out.println("\nLL 右旋後:");
        inorder(rootLL);

        // 測試 Right Right 情況 -> 左旋
        Node rootRR = new Node(10);
        rootRR.right = new Node(20);
        rootRR.right.right = new Node(30);
        rootRR.updateHeight();
        rootRR.right.updateHeight();
        System.out.println("\n\nRR 原始:");
        inorder(rootRR);
        rootRR = leftRotate(rootRR);
        System.out.println("\nRR 左旋後:");
        inorder(rootRR);

        // 測試 Left Right 情況 -> 左右旋
        Node rootLR = new Node(30);
        rootLR.left = new Node(10);
        rootLR.left.right = new Node(20);
        rootLR.updateHeight();
        rootLR.left.updateHeight();
        System.out.println("\n\nLR 原始:");
        inorder(rootLR);
        rootLR = leftRightRotate(rootLR);
        System.out.println("\nLR 左右旋後:");
        inorder(rootLR);

        // 測試 Right Left 情況 -> 右左旋
        Node rootRL = new Node(10);
        rootRL.right = new Node(30);
        rootRL.right.left = new Node(20);
        rootRL.updateHeight();
        rootRL.right.updateHeight();
        System.out.println("\n\nRL 原始:");
        inorder(rootRL);
        rootRL = rightLeftRotate(rootRL);
        System.out.println("\nRL 右左旋後:");
        inorder(rootRL);

        System.out.println();
    }
}
