import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String haystack = br.readLine(); if (haystack == null) haystack = "";
        String needle = br.readLine();   if (needle == null) needle = "";
        if (needle.length() == 0) { System.out.println(0); return; }
        System.out.println(kmp(haystack, needle));
    }

    static int kmp(String s, String p) {
        int n = s.length(), m = p.length();
        if (m > n) return -1;
        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m; ) {
            if (p.charAt(i) == p.charAt(len)) lps[i++] = ++len;
            else if (len > 0) len = lps[len - 1];
            else lps[i++] = 0;
        }
        for (int i = 0, j = 0; i < n; ) {
            if (s.charAt(i) == p.charAt(j)) { i++; j++; if (j == m) return i - m; }
            else if (j > 0) j = lps[j - 1];
            else i++;
        }
        return -1;
    }
}
