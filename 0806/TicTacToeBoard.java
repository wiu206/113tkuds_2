import java.util.Scanner;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        System.out.println("歡迎來到井字遊戲！");
        printBoard();

        while (!gameOver) {
            System.out.printf("玩家 %c 的回合。請輸入列(row)與欄(col) [0-2]：", currentPlayer);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (placeMark(row, col)) {
                printBoard();

                if (checkWin()) {
                    System.out.printf("玩家 %c 獲勝！\n", currentPlayer);
                    gameOver = true;
                } else if (isBoardFull()) {
                    System.out.println("平手！");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("無效位置，請重新輸入！");
            }
        }

        scanner.close();
    }

    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -+-+-");
        }
        System.out.println();
    }

    public static boolean placeMark(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        if (board[row][col] != ' ') return false;

        board[row][col] = currentPlayer;
        return true;
    }

    public static boolean checkWin() {
        return checkRows() || checkCols() || checkDiagonals();
    }

    public static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCols() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiagonals() {
        return (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][0] == currentPlayer);
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    // 交換玩家
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
