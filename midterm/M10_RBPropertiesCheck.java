import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {

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
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String tok = fs.next();
        if (tok == null) return;
        int n = Integer.parseInt(tok);

        int[] val = new int[n];
        char[] col = new char[n];

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(fs.next());
            String cs = fs.next();
            char c = (cs == null || cs.isEmpty()) ? 'B' : Character.toUpperCase(cs.charAt(0));
            if (v == -1) c = 'B';
            val[i] = v;
            col[i] = c;
        }

        if (n == 0 || val[0] == -1) { // 空樹視為所有性質成立
            System.out.println("RB Valid");
            return;
        }

        if (col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i >= n || val[i] == -1) continue;
            if (col[i] == 'R') {
                int L = 2 * i + 1, R = 2 * i + 2;
                if (L < n && val[L] != -1 && col[L] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (R < n && val[R] != -1 && col[R] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        int bh = blackHeight(0, n, val, col);
        if (bh == FAIL) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    static final int FAIL = Integer.MIN_VALUE / 2;

    static int blackHeight(int i, int n, int[] val, char[] col) {
        if (i >= n || val[i] == -1) return 1; // NIL 視為黑，計 1
        int L = blackHeight(2 * i + 1, n, val, col);
        if (L == FAIL) return FAIL;
        int R = blackHeight(2 * i + 2, n, val, col);
        if (R == FAIL) return FAIL;
        if (L != R) return FAIL;
        return L + (col[i] == 'B' ? 1 : 0);
    }
}
