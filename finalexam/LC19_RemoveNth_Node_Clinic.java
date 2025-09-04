import java.io.*;
import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        ListNode head = null, tail = null;
        for (int i = 0; i < n; i++) {
            ListNode node = new ListNode(fs.nextInt());
            if (head == null) head = tail = node;
            else { tail.next = node; tail = node; }
        }
        int k = fs.nextInt();

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head, slow = dummy;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        StringBuilder sb = new StringBuilder();
        for (ListNode cur = dummy.next; cur != null; cur = cur.next) {
            sb.append(cur.val).append(' ');
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
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
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
