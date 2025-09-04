import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        Integer K = fs.nextIntOrNull();
        if (K == null) { System.out.print(""); return; }
        int k = K;
        ListNode[] heads = new ListNode[k];
        for (int i = 0; i < k; i++) {
            ListNode head = null, tail = null;
            while (true) {
                int x = fs.nextInt();
                if (x == -1) break;
                ListNode node = new ListNode(x);
                if (head == null) head = tail = node;
                else { tail.next = node; tail = node; }
            }
            heads[i] = head;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(k, Comparator.comparingInt(n -> n.val));
        for (ListNode h : heads) if (h != null) pq.add(h);

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            sb.append(cur.val).append(' ');
            if (cur.next != null) pq.add(cur.next);
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.print(sb.toString());
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; }
            return buffer[ptr++];
        }
        String next() throws IOException {
            int c; do { c = read(); } while (c <= ' ' && c != -1);
            if (c == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (c > ' ') { sb.append((char)c); c = read(); }
            return sb.toString();
        }
        Integer nextIntOrNull() throws IOException { String s = next(); return s == null ? null : Integer.parseInt(s); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
