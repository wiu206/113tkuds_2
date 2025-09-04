// 題目：Sudoku Solver
// 將 9x9 的部分填寫盤面補齊為一個有效數獨解（每列/行/3x3 宮各含 1~9 且不重覆）。
// 做法：回溯 + 位元遮罩（bitmask）加速可填數字的枚舉。

class Solution {
    private static final int FULL = (1 << 9) - 1; // 9 個位元 111111111，代表 1..9

    public void solveSudoku(char[][] board) {
        int[] rows = new int[9], cols = new int[9], boxes = new int[9];

        // 先把已填的數字標記到 bitmask
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;
                int d = ch - '0';              // 1..9
                int bit = 1 << (d - 1);        // 用第 (d-1) 位表示數字 d
                int b = (r / 3) * 3 + (c / 3); // 3x3 宮的索引
                rows[r] |= bit;
                cols[c] |= bit;
                boxes[b] |= bit;
            }
        }

        dfs(board, rows, cols, boxes);
    }

    // 回溯：每次挑「候選最少」的空格（MRV），嘗試所有可填數字
    private boolean dfs(char[][] board, int[] rows, int[] cols, int[] boxes) {
        int bestR = -1, bestC = -1, bestMask = 0, bestCnt = 10;

        // 找出當前所有空格中，候選數量最少的一格
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') continue;
                int b = (r / 3) * 3 + (c / 3);
                // 可用數字 = 1..9 扣掉「該列/行/宮已出現」的數字
                int used = rows[r] | cols[c] | boxes[b];
                int mask = FULL & ~used;
                int cnt = Integer.bitCount(mask);
                if (cnt == 0) return false; // 無數可填，回溯
                if (cnt < bestCnt) {
                    bestCnt = cnt;
                    bestMask = mask;
                    bestR = r;
                    bestC = c;
                    if (cnt == 1) break;    // 已是最小，直接停止搜尋
                }
            }
            if (bestCnt == 1) break;
        }

        // 沒有空格了，成功
        if (bestR == -1) return true;

        int boxIdx = (bestR / 3) * 3 + (bestC / 3);

        // 依 bitmask 逐一嘗試可填的數字
        for (int mask = bestMask; mask != 0; mask &= (mask - 1)) {
            int bit = mask & -mask; // 取最低位的 1
            int d = Integer.numberOfTrailingZeros(bit) + 1; // 轉回數字 1..9

            // 放入 d
            board[bestR][bestC] = (char) ('0' + d);
            rows[bestR] |= bit;
            cols[bestC] |= bit;
            boxes[boxIdx] |= bit;

            if (dfs(board, rows, cols, boxes)) return true; // 成功即傳遞成功

            // 撤銷
            board[bestR][bestC] = '.';
            rows[bestR] &= ~bit;
            cols[bestC] &= ~bit;
            boxes[boxIdx] &= ~bit;
        }
        return false; // 該格嘗試完都不行，回溯
    }
}

/*
解題重點：
1) 位元遮罩 rows/cols/boxes 各 9 個整數，bit k 代表數字 (k+1) 是否已被使用。
2) 某格 (r,c) 的可填集合 = FULL & ~(rows[r] | cols[c] | boxes[boxIdx])。
3) 使用 MRV（Minimum Remaining Values）— 每步挑候選數最少的空格，可顯著減枝。
4) 用位運算枚舉候選數：mask
*/