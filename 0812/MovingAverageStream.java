import java.util.*;

public class MovingAverageStream {
    private int size;
    private Deque<Integer> window;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private TreeMap<Integer, Integer> sortedMap;
    private double sum;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new ArrayDeque<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();
        this.sortedMap = new TreeMap<>();
        this.sum = 0;
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        addNum(val);
        sortedMap.put(val, sortedMap.getOrDefault(val, 0) + 1);

        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            removeNum(removed);
            if (sortedMap.get(removed) == 1) sortedMap.remove(removed);
            else sortedMap.put(removed, sortedMap.get(removed) - 1);
        }
        return sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return sortedMap.firstKey();
    }

    public int getMax() {
        return sortedMap.lastKey();
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
        else minHeap.offer(num);
        balanceHeaps();
    }

    private void removeNum(int num) {
        if (num <= maxHeap.peek()) maxHeap.remove(num);
        else minHeap.remove(num);
        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) minHeap.offer(maxHeap.poll());
        while (minHeap.size() > maxHeap.size()) maxHeap.offer(minHeap.poll());
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}
