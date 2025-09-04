// 題目：Letter Combinations of a Phone Number
// 給定 2–9 的數字字串 digits，回傳所有可能的字母組合（電話按鍵對應）。

import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        String[] map = new String[]{
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        char[] path = new char[digits.length()];
        dfs(0, digits, map, path, res);
        return res;
    }

    private void dfs(int idx, String digits, String[] map, char[] path, List<String> res) {
        if (idx == digits.length()) {
            res.add(new String(path));
            return;
        }
        String letters = map[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            path[idx] = c;
            dfs(idx + 1, digits, map, path, res);
        }
    }
}

/*
解題思路（回溯 DFS）：
1) 用陣列 map 對應數字到字母。
2) 逐位數遞迴展開，path[] 暫存目前組合；到達長度即加入答案。
3) 邊界：digits 為空直接回傳空清單。

複雜度：
- 時間：O(3^n ~ 4^n)，n 為 digits 長度（因每位最多 4 個分支）。
- 空間：O(n) 遞迴深度與路徑。
*/
