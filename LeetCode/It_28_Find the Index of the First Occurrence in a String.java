
// 題目：Find the Index of the First Occurrence in a String
// 給定字串 haystack 與 needle，回傳 needle 在 haystack 中第一次出現的起始索引；若不存在回傳 -1。
// 解法：KMP（O(n + m)）

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int n = haystack.length(), m = needle.length();
        int[] lps = buildLPS(needle);  // longest prefix which is also suffix

        int i = 0, j = 0; // i 掃 haystack，j 掃 needle
        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
                if (j == m) return i - j; // 完整匹配
            } else {
                if (j > 0) {
                    j = lps[j - 1];      // 失配時用 lps 跳回
                } else {
                    i++;                 // j==0 時只能前進 i
                }
            }
        }
        return -1;
    }

    // 構建 LPS（部分匹配表）
    private int[] buildLPS(String p) {
        int m = p.length();
        int[] lps = new int[m];
        int len = 0; // 目前最長的前綴=後綴長度
        for (int i = 1; i < m; ) {
            if (p.charAt(i) == p.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}

/*
解題思路（KMP）：
1) 預處理 needle 得到 lps[]，lps[k] 表示以 k 結尾子字串的「最長相等前綴＝後綴」長度。
2) 掃描 haystack 遇到失配時，用 lps 讓 j 回跳到可延續的最長前綴處，避免重複比較。

複雜度：
- 時間：O(n + m)
- 空間：O(m)

小備註：
- 若不在意效率，也可直接用 Java 內建：return haystack.indexOf(needle);
*/
