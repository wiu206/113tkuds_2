import java.io.*;
import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), m = fs.nextInt();
        ListNode a = null, at = null, b = null, bt = null;
        for (int i = 0; i < n; i++) {
            ListNode node = new ListNode(fs.nextInt());
            if (a == null) a = at = node; else { at.next = node; at = node; }
        }
        for (int i = 0; i < m; i++) {
            ListNode node = new ListNode(fs.nextInt());
            if (b == null) b = bt = node; else { bt.next = node; bt = node; }
        }
        ListNode head = merge(a, b);
        StringBuilder sb = new StringBuilder();
        for (ListNode cur = head; cur != null; cur = cur.next) sb.append(cur.val).append(' ');
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        System.out.print(sb.toString());
    }

    static ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0), t = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) { t.next = a; a = a.next; }
            else { t.next = b; b = b.next; }
            t = t.next;
        }
        t.next = (a != null) ? a : b;
        return dummy.next;
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
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
