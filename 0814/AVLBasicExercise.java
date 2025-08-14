public class AVLBasicExercise {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, int data) {
        if (node == null) return new Node(data);
        if (data < node.data) {
            node.left = insertRec(node.left, data);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data);
        }
        return node;
    }

    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchRec(node.left, data);
        return searchRec(node.right, data);
    }

    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return 0;
        return Math.max(heightRec(node.left), heightRec(node.right)) + 1;
    }

    public boolean isValidAVL() {
        return checkAVL(root) != -1;
    }

    private int checkAVL(Node node) {
        if (node == null) return 0;
        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);
        if (leftHeight == -1 || rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(4);
        tree.insert(15);

        System.out.println("搜尋 15: " + tree.search(15));
        System.out.println("搜尋 99: " + tree.search(99));
        System.out.println("樹的高度: " + tree.height());
        System.out.println("是否為有效 AVL: " + tree.isValidAVL());
    }
}
