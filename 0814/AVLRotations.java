public class AVLRotations {

    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = (x != null) ? x.right : null;

        // 執行旋轉
        x.right = y;
        y.left = T2;

        // 更新高度（先子後父）
        y.updateHeight();
        x.updateHeight();

        return x; // 新根
    }

    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = (y != null) ? y.left : null;

        // 執行旋轉
        y.left = x;
        x.right = T2;

        // 更新高度（先子後父）
        x.updateHeight();
        y.updateHeight();

        return y; // 新根
    }

    // 自動由下而上重算高度（方便測試）
    public static int recomputeHeights(AVLNode node) {
        if (node == null) return 0;
        int lh = recomputeHeights(node.left);
        int rh = recomputeHeights(node.right);
        node.height = Math.max(lh, rh) + 1;
        return node.height;
    }

    // 中序走訪印出樹（已改用 data）
    public static void inorderPrint(AVLNode node) {
        if (node != null) {
            inorderPrint(node.left);
            System.out.print(node.data + " ");
            inorderPrint(node.right);
        }
    }

    public static void main(String[] args) {
        // 建一棵造成「左左不平衡（LL）」的小樹，右旋後會平衡
        //       30
        //      /
        //     20
        //    /
        //   10
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);
        recomputeHeights(root);

        System.out.println("原始樹 (中序)：");
        inorderPrint(root);

        System.out.println("\n--- 對根做右旋（修正LL）---");
        root = rightRotate(root);
        inorderPrint(root);

        // 再造一個「右右不平衡（RR）」場景，左旋後會平衡
        //   在目前根為20的狀態下接 30->40 成 RR
        root.right = new AVLNode(30);
        root.right.right = new AVLNode(40);
        recomputeHeights(root);

        System.out.println("\n\n--- 對根做左旋（修正RR）---");
        root = leftRotate(root);
        inorderPrint(root);
        System.out.println();
    }
}
