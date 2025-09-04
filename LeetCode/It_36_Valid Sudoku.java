// 題目：Valid Sudoku
// 檢查 9x9 的數獨盤面是否有效（僅檢查已填入的格子）。
// 規則：每列、每行、每個 3x3 宮都不能出現重複的 1~9。

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9]; // box 編號： (r/3)*3 + (c/3)

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;

                int d = ch - '0';          // 1..9
                int bit = 1 << d;
                int b = (r / 3) * 3 + (c / 3);

                // 若該數字在列/行/宮中已出現過，則無效
                if ((rows[r] & bit) != 0) return false;
                if ((cols[c] & bit) != 0) return false;
                if ((boxes[b] & bit) != 0) return false;

                // 標記出現
                rows[r] |= bit;
                cols[c] |= bit;
                boxes[b] |= bit;
            }
        }
        return true;
    }
}

/*
解題思路（位元遮罩）：
- 對每列、每行、每個 3x3 宮分別用一個 int 當作出現集合，bit d 代表數字 d 是否出現。
- 掃描每個格子，遇到數字 d：
  * 算出其 bit = 1<<d 與 box 索引 (r/3)*3 + (c/3)
  * 若該 bit 在對應列/行/宮已設置，代表重複 -> 回傳 false
  * 否則把 bit 設起來
- 忽略 '.'。

複雜度：
- 時間 O(81) ≈ O(1)
- 空間 O(1)
*/
