public class RecursiveMathCalculator {

    public static void main(String[] args) {
        System.out.println("C(5, 2) = " + combination(5, 2));
        System.out.println("Catalan(4) = " + catalan(4));
        System.out.println("Hanoi(3) 步數 = " + hanoiMoves(3));
        System.out.println("12321 是否為回文數：" + isPalindrome(12321));
        System.out.println("12345 是否為回文數：" + isPalindrome(12345));
    }

    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int catalan(int n) {
        if (n == 0) return 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += catalan(i) * catalan(n - 1 - i);
        }
        return sum;
    }

    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    public static boolean isPalindrome(int num) {
        return num == reverse(num, 0);
    }

    public static int reverse(int num, int rev) {
        if (num == 0) return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }
}
