// 題目：Integer to Roman
// 給定整數 num (1 <= num <= 3999)，將其轉換為羅馬數字字串。

class Solution {
    public String intToRoman(int num) {
        // 由大到小列出所有面額，包含減法記號（如 900: CM、4: IV）
        int[] vals =    {1000, 900, 500, 400, 100,  90,  50,  40,  10,   9,   5,   4,   1};
        String[] syms = {"M",  "CM","D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vals.length && num > 0; i++) {
            while (num >= vals[i]) {   // 能用當前面額就一直取
                sb.append(syms[i]);
                num -= vals[i];
            }
        }
        return sb.toString();
    }
}

/*
解題思路（Greedy）：
1. 羅馬數字使用由大到小的面額組成，並包含 6 種減法表示：
   900(CM), 400(CD), 90(XC), 40(XL), 9(IX), 4(IV)。
2. 依序從最大面額開始，能減就減並把對應符號加到結果字串，直到 num 變 0。

複雜度：
- 時間：O(1)（面額種類固定 13 個；實作上 ≈ O(字元數)）
- 空間：O(1)

備註（另一個常見寫法）：
- 也可預先建立千位、百位、十位、個位的對照表，直接拼接四段字串。
*/
