import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) return 0;
        PriorityQueue<Integer> profits = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        while (i < prices.length) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
            int buy = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
            int sell = prices[i];
            if (sell > buy) profits.offer(sell - buy);
            i++;
        }
        int total = 0;
        while (k > 0 && !profits.isEmpty()) {
            total += profits.poll();
            k--;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2,4,1}));
        System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3}));
        System.out.println(maxProfit(2, new int[]{1,2,3,4,5}));
    }
}
