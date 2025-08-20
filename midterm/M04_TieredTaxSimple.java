import java.io.*;

public class M04_TieredTaxSimple {

   static long taxOf(long x) {
    long tax = 0;
    long a = Math.min(x, 120_000);
    if (a > 0) tax += a * 5 / 100;

    if (x > 120_000) {
        long b = Math.min(x, 500_000) - 120_000;
        if (b > 0) tax += b * 12 / 100;
    }
    if (x > 500_000) {
        long c = Math.min(x, 1_000_000) - 500_000;
        if (c > 0) tax += c * 20 / 100;
    }
    if (x > 1_000_000) {
        long d = x - 1_000_000;
        tax += d * 30 / 100;
    }
    return tax;
}


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        int n = Integer.parseInt(s.trim());

        long sum = 0;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String line;
            while ((line = br.readLine()) != null && line.trim().isEmpty()) {}
            long income = Long.parseLong(line.trim());
            long t = taxOf(income);
            sum += t;
            out.append("Tax: ").append(t).append('\n');
        }
        long avg = (sum + n / 2) / n; // 四捨五入
        out.append("Average: ").append(avg);
        System.out.print(out.toString());
    }
}
/*
 * Time Complexity: O(n)
 * 說明：對每筆收入做固定段距的常數次運算，單筆 O(1)，共 n 筆為 O(n)。
 *      僅使用整數運算與一次輸入走訪，空間為 O(1)（不含輸入）。
 */
