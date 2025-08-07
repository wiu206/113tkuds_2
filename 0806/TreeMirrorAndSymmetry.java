public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹：" + isSymmetric(root1));

        TreeNode mirror = mirrorTree(copy(root1));
        System.out.println("鏡像後是否仍為對稱：" + isSymmetric(mirror));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(2);

        System.out.println("root2 和 root3 是否互為鏡像：" + isMirror(root2, root3));

        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(3);
        sub.right = new TreeNode(4);
        System.out.println("sub 是否為 root1 的子樹：" + isSubtree(root1, sub));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public static TreeNode mirrorTree(TreeNode node) {
        if (node == null) return null;
        TreeNode left = mirrorTree(node.left);
        TreeNode right = mirrorTree(node.right);
        node.left = right;
        node.right = left;
        return node;
    }

    public static boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) return false;
        if (isSameTree(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }

    public static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    public static TreeNode copy(TreeNode node) {
        if (node == null) return null;
        TreeNode newNode = new TreeNode(node.val);
        newNode.left = copy(node.left);
        newNode.right = copy(node.right);
        return newNode;
    }
}
