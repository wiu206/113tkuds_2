import java.io.*;
import java.util.*;

public class M07_BinaryTreeLeftView {

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
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Node root = buildFromLevelOrder(a);
        List<Integer> leftView = getLeftView(root);

        StringBuilder sb = new StringBuilder();
        sb.append("LeftView:");
        if (!leftView.isEmpty()) {
            sb.append(' ');
            for (int i = 0; i < leftView.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(leftView.get(i));
            }
        }
        System.out.print(sb.toString());
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

    static List<Integer> getLeftView(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                if (i == 0) res.add(cur.val);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
        }
        return res;
    }
}
