public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        
        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A：");
        printMatrix(matrixA);

        System.out.println("矩陣 B：");
        printMatrix(matrixB);

        System.out.println("A + B：");
        printMatrix(addMatrices(matrixA, matrixB));

        System.out.println("A × C：");
        printMatrix(multiplyMatrices(matrixA, matrixC));

        System.out.println("A 的轉置：");
        printMatrix(transposeMatrix(matrixA));

        System.out.println("A 的最大值與最小值：");
        int[] minMax = findMinMax(matrixA);
        System.out.println("最大值：" + minMax[1] + ", 最小值：" + minMax[0]);
    }

    public static int[][] addMatrices(int[][] m1, int[][] m2) {
        int rows = m1.length;
        int cols = m1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(int[][] m1, int[][] m2) {
        int rows = m1.length;
        int cols = m2[0].length;
        int common = m2.length;

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < common; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
    }

    public static int[] findMinMax(int[][] matrix) {
        int min = matrix[0][0];
        int max = matrix[0][0];

        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) min = value;
                if (value > max) max = value;
            }
        }
        return new int[]{min, max};
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}
