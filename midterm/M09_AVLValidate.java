import java.io.*;
import java.util.*;

public class M09_AVLValidate {

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws Exception { return Integer.parseInt(next()); }
    }

    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String t = fs.next();
        if (t == null) return;
        int n = Integer.parseInt(t);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Node root = buildFromLevelOrder(a);

        boolean bstOk = isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        if (!bstOk) {
            System.out.println("Invalid BST");
            return;
        }
        boolean avlOk = isAVL(root);
        System.out.println(avlOk ? "Valid" : "Invalid AVL");
    }

    static Node buildFromLevelOrder(int[] arr) {
        int n = arr.length;
        if (n == 0 || arr[0] == -1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < n) {
            Node cur = q.poll();
            if (i < n && arr[i] != -1) {
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < n && arr[i] != -1) {
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    static boolean isBST(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    static boolean isAVL(Node root) {
        return heightOrFail(root) != BAD;
    }

    static final int BAD = Integer.MIN_VALUE;

    static int heightOrFail(Node node) {
        if (node == null) return -1;
        int hl = heightOrFail(node.left);
        if (hl == BAD) return BAD;
        int hr = heightOrFail(node.right);
        if (hr == BAD) return BAD;
        if (Math.abs(hl - hr) > 1) return BAD;
        return Math.max(hl, hr) + 1;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：建樹 O(n)；檢查 BST 以上下界遞迴遍歷每節點一次 O(n)；
 *      檢查 AVL 以後序一次遍歷計算高度並驗證平衡 O(n)。總體仍為 O(n)。
 *      額外空間：O(w) 佇列建樹（最寬層），遞迴堆疊 O(h)；在最壞情況下 O(n)。
 */
