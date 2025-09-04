import java.io.*;
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        Integer N = fs.nextIntOrNull();
        if (N == null) return;
        int n = N, val = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        int write = 0;
        for (int i = 0; i < n; i++) if (a[i] != val) a[write++] = a[i];

        System.out.println(write);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < write; i++) { if (i > 0) sb.append(' '); sb.append(a[i]); }
        System.out.println(sb.toString());
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
