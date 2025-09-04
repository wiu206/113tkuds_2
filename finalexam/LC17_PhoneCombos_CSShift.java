import java.io.*;
import java.util.*;

public class LC17_PhoneCombos_CSShift {
    static String digits;
    static String[] map = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    static StringBuilder path = new StringBuilder();
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        digits = br.readLine();
        if (digits == null) digits = "";
        digits = digits.trim();
        if (digits.isEmpty()) return;
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        dfs(0);
        out.flush();
    }

    static void dfs(int idx) {
        if (idx == digits.length()) {
            out.println(path.toString());
            return;
        }
        char d = digits.charAt(idx);
        String s = map[d - '2'];
        for (int i = 0; i < s.length(); i++) {
            path.append(s.charAt(i));
            dfs(idx + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
