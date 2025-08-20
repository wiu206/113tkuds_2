import java.io.*;
import java.util.*;

public class M03_TopKConvenience {

    static class Item {
        String name;
        int qty;
        int idx; 
        Item(String name, int qty, int idx) {
            this.name = name;
            this.qty = qty;
            this.idx = idx;
        }
    }

    static final Comparator<Item> MIN_HEAP_CMP = (a, b) -> {
        if (a.qty != b.qty) return Integer.compare(a.qty, b.qty);          
        int nameCmp = b.name.compareTo(a.name);                        
        if (nameCmp != 0) return nameCmp;
        return Integer.compare(b.idx, a.idx);   
    };

    static final Comparator<Item> OUTPUT_CMP = (a, b) -> {
        if (a.qty != b.qty) return Integer.compare(b.qty, a.qty);
        int nameCmp = a.name.compareTo(b.name);
        if (nameCmp != 0) return nameCmp;
        return Integer.compare(a.idx, b.idx);
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        while (first != null && first.trim().isEmpty()) first = br.readLine();
        StringTokenizer st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Item> pq = new PriorityQueue<>(K + 1, MIN_HEAP_CMP);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) line = br.readLine();
            st = new StringTokenizer(line);
            String name = st.nextToken();        
            int qty = Integer.parseInt(st.nextToken());
            pq.offer(new Item(name, qty, i));
            if (pq.size() > K) pq.poll();         
        }

        List<Item> ans = new ArrayList<>(pq);
        ans.sort(OUTPUT_CMP);

        StringBuilder sb = new StringBuilder();
        for (Item it : ans) {
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：逐筆讀入 n 筆資料，對大小為 K 的 Min‑Heap 進行插入與可能的刪除，
 *       每次操作成本為 O(log K)，因此總時間為 O(n log K)。最終輸出前
 *       將堆中至多 K 筆做一次排序，成本 O(K log K)，相對於 O(n log K) 為次要項。
 */
