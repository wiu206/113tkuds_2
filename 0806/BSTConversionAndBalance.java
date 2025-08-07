public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class DoublyListNode {
        int val;
        DoublyListNode prev, next;
        DoublyListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedRoot = sortedArrayToBST(sortedArray, 0, sortedArray.length - 1);
        System.out.println("是否為平衡BST：" + isBalanced(balancedRoot));
        System.out.println("平衡因子：" + getBalanceFactor(balancedRoot));

        TreeNode sumBST = clone(balancedRoot);
        convertToGreaterSumTree(sumBST);
        System.out.print("轉換為 Greater Sum Tree 的中序：");
        printInorder(sumBST);
        System.out.println();

        TreeNode bst = new TreeNode(4);
        bst.left = new TreeNode(2);
        bst.right = new TreeNode(6);
        bst.left.left = new TreeNode(1);
        bst.left.right = new TreeNode(3);
        bst.right.left = new TreeNode(5);
        bst.right.right = new TreeNode(7);

        DoublyListNode head = bstToDoublyList(bst);
        System.out.print("BST 轉雙向串列（正向）：");
        DoublyListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            if (p.next == null) break;
            p = p.next;
        }
        System.out.print("\nBST 轉雙向串列（反向）：");
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.prev;
        }
        System.out.println();
    }

    public static TreeNode sortedArrayToBST(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = sortedArrayToBST(arr, l, mid - 1);
        node.right = sortedArrayToBST(arr, mid + 1, r);
        return node;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    public static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        int right = checkHeight(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    public static int getBalanceFactor(TreeNode node) {
        return height(node.left) - height(node.right);
    }

    public static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    static int sum = 0;
    public static void convertToGreaterSumTree(TreeNode node) {
        sum = 0;
        reverseInorder(node);
    }

    public static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        sum += node.val;
        node.val = sum;
        reverseInorder(node.left);
    }

    static DoublyListNode prev = null, head = null;
    public static DoublyListNode bstToDoublyList(TreeNode root) {
        prev = null;
        head = null;
        inorderToList(root);
        return head;
    }

    public static void inorderToList(TreeNode node) {
        if (node == null) return;
        inorderToList(node.left);
        DoublyListNode curr = new DoublyListNode(node.val);
        if (prev == null) head = curr;
        else {
            prev.next = curr;
            curr.prev = prev;
        }
        prev = curr;
        inorderToList(node.right);
    }

    public static void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }

    public static TreeNode clone(TreeNode node) {
        if (node == null) return null;
        TreeNode copy = new TreeNode(node.val);
        copy.left = clone(node.left);
        copy.right = clone(node.right);
        return copy;
    }
}
