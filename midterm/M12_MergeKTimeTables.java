import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {

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

    static class Node {
        int t, li, idx;
        Node(int t, int li, int idx) { this.t = t; this.li = li; this.idx = idx; }
    }

    static int parseTimeToMinutes(String s) {
        if (s.indexOf(':') >= 0) {
            String[] p = s.split(":");
            return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
        }
        return Integer.parseInt(s);
    }

    static String toHHmm(int m) {
        int h = m / 60, mm = m % 60;
        String hs = (h < 10 ? "0" : "") + h;
        String ms = (mm < 10 ? "0" : "") + mm;
        return hs + ":" + ms;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String tok = fs.next();
        if (tok == null) return;
        int K = Integer.parseInt(tok);

        List<int[]> lists = new ArrayList<>(K);
        boolean useHHmm = false;
        boolean decidedFormat = false;

        for (int i = 0; i < K; i++) {
            int len = fs.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                String t = fs.next();
                if (!decidedFormat) { useHHmm = t.indexOf(':') >= 0; decidedFormat = true; }
                arr[j] = parseTimeToMinutes(t);
            }
            lists.add(arr);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.t));
        for (int i = 0; i < K; i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) pq.offer(new Node(arr[0], i, 0));
        }

        StringBuilder out = new StringBuilder();
        boolean firstOut = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!firstOut) out.append(' ');
            firstOut = false;
            out.append(useHHmm ? toHHmm(cur.t) : cur.t);
            int[] arr = lists.get(cur.li);
            int ni = cur.idx + 1;
            if (ni < arr.length) pq.offer(new Node(arr[ni], cur.li, ni));
        }

        System.out.print(out.toString());
    }
}
