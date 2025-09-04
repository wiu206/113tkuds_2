class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) return s;

        int start = 0, end = 0; // 最長回文的左右端點（含）
        for (int i = 0; i < n; i++) {
            int len1 = expand(s, i, i);     // 以 i 為中心（奇數長度）
            int len2 = expand(s, i, i + 1); // 以 i,i+1 為中心（偶數長度）
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                // 由中心回推左右端點
                start = i - (len - 1) / 2;
                end   = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 從左右指標向外擴展，回傳回文長度
    private int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return r - l - 1; // 擴過頭一格，減回來
    }
}