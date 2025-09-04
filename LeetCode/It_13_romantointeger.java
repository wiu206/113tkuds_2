// 題目：Roman to Integer
// 給定羅馬數字字串 s，將其轉換為整數（1 ~ 3999）。

class Solution {
    public int romanToInt(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = val(s.charAt(i));
            // 若下一個字元代表的值更大，表示採用減法規則（如 IV、IX、XL、XC、CD、CM）
            if (i + 1 < n && cur < val(s.charAt(i + 1))) {
                ans -= cur;
            } else {
                ans += cur;
            }
        }
        return ans;
    }

    // 對應羅馬符號到數值
    private int val(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0; // 題目保證輸入有效，這行只是保險
        }
    }
}

/*
解題思路：
1. 一般情況下將每個符號的值相加；
2. 若出現「小在大前」的組合（六種：IV、IX、XL、XC、CD、CM），
   代表要把前面的小值改為相減，因此在掃描中：
   - 若 cur < next，就 ans -= cur；
   - 否則 ans += cur。

複雜度：
- 時間：O(n)
- 空間：O(1)

範例：
- "III" -> 3
- "LVIII" -> 58  (L=50, V=5, III=3)
- "MCMXCIV" -> 1994 (M=1000, CM=900, XC=90, IV=4)
*/
