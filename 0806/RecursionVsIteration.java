public class RecursionVsIteration {

    public static void main(String[] args) {
        System.out.println("1. 二項式係數 C(5, 2)：");
        System.out.println("遞迴：" + binomialRecursive(5, 2));
        System.out.println("迭代：" + binomialIterative(5, 2));

        System.out.println("\n2. 陣列乘積 [2, 3, 4, 5]：");
        int[] arr = {2, 3, 4, 5};
        System.out.println("遞迴：" + productRecursive(arr, 0));
        System.out.println("迭代：" + productIterative(arr));

        System.out.println("\n3. 字串元音數量 'RecursionVsIteration'：");
        String s = "RecursionVsIteration";
        System.out.println("遞迴：" + countVowelsRecursive(s, 0));
        System.out.println("迭代：" + countVowelsIterative(s));

        System.out.println("\n4. 括號配對判斷 '(()())'：");
        String p = "(()())";
        System.out.println("遞迴：" + isBalancedRecursive(p, 0, 0));
        System.out.println("迭代：" + isBalancedIterative(p));
    }

    public static int binomialRecursive(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRecursive(n - 1, k - 1) + binomialRecursive(n - 1, k);
    }

    public static int binomialIterative(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    public static int productRecursive(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static int productIterative(int[] arr) {
        int product = 1;
        for (int num : arr) {
            product *= num;
        }
        return product;
    }

    public static int countVowelsRecursive(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + countVowelsRecursive(s, index + 1);
    }

    public static int countVowelsIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) >= 0) count++;
        }
        return count;
    }

    public static boolean isBalancedRecursive(String s, int index, int count) {
        if (count < 0) return false;
        if (index == s.length()) return count == 0;
        if (s.charAt(index) == '(') return isBalancedRecursive(s, index + 1, count + 1);
        if (s.charAt(index) == ')') return isBalancedRecursive(s, index + 1, count - 1);
        return isBalancedRecursive(s, index + 1, count);
    }

    public static boolean isBalancedIterative(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}
