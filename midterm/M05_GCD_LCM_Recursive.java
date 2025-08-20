import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {

    static long gcd(long x, long y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        long a = sc.nextLong();
        long b = sc.nextLong();
        long g = gcd(a, b);
        long l = (a / g) * b;  
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
        sc.close();
    }
}

/*
 * Time Complexity: O(log min(a, b))
 * 說明：遞迴歐幾里得每步以 x%y 使參數快速縮小，步數為 O(log min(a,b))。
 *      LCM 以 a/g*b 計算為 O(1)；遞迴深度 O(log min(a,b))，額外空間同階。
 */
