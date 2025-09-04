import java.io.*;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++) A[i] = fs.nextDouble();
        for (int i = 0; i < m; i++) B[i] = fs.nextDouble();
        System.out.printf("%.1f%n", median(A, B));
    }

    private static double median(double[] A, double[] B) {
        if (A.length > B.length) return median(B, A);
        int n = A.length, m = B.length, low = 0, high = n, half = (n + m + 1) / 2;
        double INF = Double.POSITIVE_INFINITY, NINF = Double.NEGATIVE_INFINITY;
        while (true) {
            int i = (low + high) >>> 1;
            int j = half - i;
            double L1 = (i == 0) ? NINF : A[i - 1];
            double R1 = (i == n) ? INF : A[i];
            double L2 = (j == 0) ? NINF : B[j - 1];
            double R2 = (j == m) ? INF : B[j];
            if (L1 <= R2 && L2 <= R1) {
                if (((n + m) & 1) == 1) return Math.max(L1, L2);
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
            } else if (L1 > R2) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
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
        double nextDouble() throws IOException { return Double.parseDouble(next()); }
    }
}
