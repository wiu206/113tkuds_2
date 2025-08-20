import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {

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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String t = fs.next();
        if (t == null) return;
        int n = Integer.parseInt(t);
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            int score = fs.nextInt();
            a[i] = (((long) score) << 32) | (i & 0xffffffffL); // key: (score, index)
        }

        for (int i = (n >>> 1) - 1; i >= 0; i--) siftDown(a, n, i);  // build max-heap
        for (int end = n - 1; end > 0; end--) {
            long tmp = a[0]; a[0] = a[end]; a[end] = tmp;
            siftDown(a, end, 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int score = (int)(a[i] >> 32);
            if (i > 0) sb.append(' ');
            sb.append(score);
        }
        System.out.print(sb.toString());
    }

    static void siftDown(long[] a, int n, int i) {
        while (true) {
            int l = (i << 1) + 1;
            if (l >= n) break;
            int r = l + 1, best = l;
            if (r < n && a[r] > a[l]) best = r;      // larger key = (score大，或同分index大)
            if (a[i] >= a[best]) break;
            long t = a[i]; a[i] = a[best]; a[best] = t;
            i = best;
        }
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：先以 Bottom-up 建立 Max-Heap O(n)，再進行 n 次取最大與下沉，
 *      每次 O(log n)，總計 O(n log n)。鍵值以 (score,index) 合併，確保同分時索引小者在前。
 */
