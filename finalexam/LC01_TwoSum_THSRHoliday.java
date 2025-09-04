import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long target = fs.nextLong();
        HashMap<Long, Integer> needIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long x = fs.nextLong();
            Integer j = needIdx.get(x);
            if (j != null) {
                System.out.println(j + " " + i);
                return;
            }
            needIdx.putIfAbsent(target - x, i);
        }
        System.out.println("-1 -1");
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

        long nextLong() throws IOException {
            int c; do { c = read(); } while (c <= ' ');
            boolean neg = (c == '-');
            if (neg) c = read();
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
    }
}
