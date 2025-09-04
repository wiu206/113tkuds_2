import java.io.*;
import java.util.*;

public class LC39_CombinationSum_PPE {
    static int n, target;
    static int[] a;
    static List<Integer> path = new ArrayList<>();
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        n = fs.nextInt();
        target = fs.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();
        Arrays.sort(a);
        dfs(0, target);
        System.out.print(out.toString());
    }

    static void dfs(int start, int rem) {
        if (rem == 0) {
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) out.append(' ');
                out.append(path.get(i));
            }
            out.append('\n');
            return;
        }
        for (int i = start; i < n; i++) {
            if (i > start && a[i] == a[i - 1]) continue;
            if (a[i] > rem) break;
            path.add(a[i]);
            dfs(i, rem - a[i]);
            path.remove(path.size() - 1);
        }
    }

    private static class FastScanner {
        private final InputStream in; private final byte[] buffer = new byte[1<<16];
        private int ptr=0,len=0; FastScanner(InputStream is){in=is;}
        private int read() throws IOException { if (ptr>=len){len=in.read(buffer);ptr=0;if(len<=0)return-1;} return buffer[ptr++]; }
        String next() throws IOException { int c; do{c=read();}while(c<=32&&c!=-1); if(c==-1)return null; StringBuilder sb=new StringBuilder(); while(c>32){sb.append((char)c); c=read();} return sb.toString(); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}
