import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {

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

        int L = fs.nextInt();
        int R = fs.nextInt();
        if (L > R) { int tmp = L; L = R; R = tmp; }

        long sum = rangeSum(root, L, R);
        System.out.println("Sum: " + sum);
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

    static long rangeSum(Node node, int L, int R) {
        if (node == null) return 0;
        if (node.val < L) return rangeSum(node.right, L, R);
        if (node.val > R) return rangeSum(node.left, L, R);
        return node.val + rangeSum(node.left, L, R) + rangeSum(node.right, L, R);
    }
}
