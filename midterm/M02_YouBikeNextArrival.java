import java.io.*;

public class M02_YouBikeNextArrival {

    private static int toMinutes(String t) {
        String[] p = t.trim().split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    private static String toHHmm(int m) {
        int h = m / 60, mm = m % 60;
        String hs = (h < 10 ? "0" : "") + h;
        String ms = (mm < 10 ? "0" : "") + mm;
        return hs + ":" + ms;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        int n = Integer.parseInt(line.trim());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            String t = br.readLine();
            while (t != null && t.trim().isEmpty()) t = br.readLine();
            arr[i] = toMinutes(t.trim());
        }

        String q = br.readLine();
        while (q != null && q.trim().isEmpty()) q = br.readLine();
        int query = toMinutes(q.trim());

        int lo = 0, hi = n; 
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= query) lo = mid + 1;
            else hi = mid;
        }

        if (lo < n) {
            System.out.println(toHHmm(arr[lo]));
        } else {
            System.out.println("No bike");
        }
    }
}
