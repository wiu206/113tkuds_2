import java.io.*;

public class M06_PalindromeClean {
    static boolean isAsciiLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
    static char toLowerAscii(char c) {
        return (c >= 'A' && c <= 'Z') ? (char)(c + 32) : c;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";
        int i = 0, j = s.length() - 1;
        boolean ok = true;
        while (i < j) {
            while (i < j && !isAsciiLetter(s.charAt(i))) i++;
            while (i < j && !isAsciiLetter(s.charAt(j))) j--;
            if (i < j) {
                if (toLowerAscii(s.charAt(i)) != toLowerAscii(s.charAt(j))) { ok = false; break; }
                i++; j--;
            }
        }
        System.out.println(ok ? "Yes" : "No");
    }
}
