
// 題目：Substring with Concatenation of All Words
// 給定字串 s 與字串陣列 words（每個 word 長度相同），找出 s 中
// 由 words 所有字串「各一次、任意順序」連接而成的子字串起始索引。

import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return ans;

        int n = s.length();
        int m = words.length;
        int w = words[0].length();
        int needLen = m * w;
        if (n < needLen) return ans;

        // 需求表：每個單字應出現次數
        Map<String, Integer> need = new HashMap<>();
        for (String t : words) need.put(t, need.getOrDefault(t, 0) + 1);

        // 依照起點偏移量（0..w-1）做「字長步進」滑動視窗
        for (int shift = 0; shift < w; shift++) {
            int left = shift, count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = shift; right + w <= n; right += w) {
                String cur = s.substring(right, right + w);

                if (!need.containsKey(cur)) {
                    // 視窗內所有字作廢，從下一格重來
                    window.clear();
                    count = 0;
                    left = right + w;
                    continue;
                }

                // 放入當前單字
                window.put(cur, window.getOrDefault(cur, 0) + 1);
                count++;

                // 若某字過多，從左邊收縮直到合法
                while (window.get(cur) > need.get(cur)) {
                    String out = s.substring(left, left + w);
                    window.put(out, window.get(out) - 1);
                    left += w;
                    count--;
                }

                // 若視窗剛好包含 m 個字（各次數皆符合），記錄答案
                if (count == m) {
                    ans.add(left);
                    // 視窗右移一格（移出最左邊那個字）
                    String out = s.substring(left, left + w);
                    window.put(out, window.get(out) - 1);
                    left += w;
                    count--;
                }
            }
        }
        return ans;
    }
}

/*
解題思路（滑動視窗 + 固定步長）：
1. words 中每個字長度相同為 w，整段拼接長度為 m*w。
2. 以 0..w-1 的「偏移量」作為起點，每次右指標以 w 為步長取字，視窗內用 HashMap 統計。
3. 若某字出現次數超過需求，從左側每次移出一個字（步長 w）直到合法。
4. 當視窗內字數 count == m，代表匹配成功，記錄 left，並把左端再移出一個字以尋找下一解。

複雜度：
- 時間：O(w * (n / w)) ≈ O(n) 但每步包含 map 操作，嚴格上為 O(n) ~ O(n * logU)，U 為不同單字數
- 空間：O(U) 用於需求表與視窗表
*/
