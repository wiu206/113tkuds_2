import java.io.*;
import java.util.*;

public class M01_BuildHeap {

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException {
            int c, sgn = 1, x = 0;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == '-') { sgn = -1; c = read(); }
            for (; c > ' '; c = read()) x = x * 10 + (c - '0');
            return x * sgn;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String type = fs.next();
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        boolean isMax = "max".equalsIgnoreCase(type);
        for (int i = (n >>> 1) - 1; i >= 0; i--) {
            heapifyDown(a, n, i, isMax);
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        System.out.print(out.toString());
    }

    private static void heapifyDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int left = (i << 1) + 1;
            if (left >= n) break;
            int right = left + 1;

            int best = left;
            if (right < n) {
                if (isMax) {
                    if (a[right] > a[left]) best = right;
                } else {
                    if (a[right] < a[left]) best = right;
                }
            }

            if (isMax) {
                if (a[i] >= a[best]) break;
            } else {
                if (a[i] <= a[best]) break;
            }

            int tmp = a[i]; a[i] = a[best]; a[best] = tmp;
            i = best;
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，從 (n/2−1) 開始往上做 heapifyDown。
 *      每個節點最多下沉高度 h，所有節點高度總和為 O(n)，
 *      因此整體時間複雜度為 O(n)，優於逐一插入的 O(n log n)。
 */
