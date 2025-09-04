import java.io.*;
import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long[] h = new long[n];
        for (int i = 0; i < n; i++) h[i] = fs.nextLong();
        int l = 0, r = n - 1;
        long ans = 0;
        while (l < r) {
            long height = Math.min(h[l], h[r]);
            ans = Math.max(ans, (long)(r - l) * height);
            if (h[l] <= h[r]) l++;
            else r--;
        }
        System.out.println(ans);
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
