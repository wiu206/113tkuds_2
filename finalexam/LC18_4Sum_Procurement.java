import java.io.*;
import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long target = fs.nextLong();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();
        Arrays.sort(a);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue;
            long minI = (long)a[i] + a[i + 1] + a[i + 2] + a[i + 3];
            if (minI > target) break;
            long maxI = (long)a[i] + a[n - 1] + a[n - 2] + a[n - 3];
            if (maxI < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) continue;
                long minJ = (long)a[i] + a[j] + a[j + 1] + a[j + 2];
                if (minJ > target) break;
                long maxJ = (long)a[i] + a[j] + a[n - 1] + a[n - 2];
                if (maxJ < target) continue;

                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = (long)a[i] + a[j] + a[l] + a[r];
                    if (sum == target) {
                        sb.append(a[i]).append(' ').append(a[j]).append(' ').append(a[l]).append(' ').append(a[r]).append('\n');
                        l++; r--;
                        while (l < r && a[l] == a[l - 1]) l++;
                        while (l < r && a[r] == a[r + 1]) r--;
                    } else if (sum < target) l++;
                    else r--;
                }
            }
        }
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
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }
}
