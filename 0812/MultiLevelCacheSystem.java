import java.util.*;

class CacheEntry {
    int key;
    String value;
    int freq;
    long time;
    int level;

    CacheEntry(int key, String value, int freq, long time, int level) {
        this.key = key;
        this.value = value;
        this.freq = freq;
        this.time = time;
        this.level = level;
    }

    double score(int cost) {
        return -(freq * 1.0 / cost);
    }
}

public class MultiLevelCacheSystem {
    private int[] capacity = {2, 5, 10};
    private int[] cost = {1, 3, 10};
    private Map<Integer, CacheEntry> map = new HashMap<>();
    private PriorityQueue<CacheEntry>[] heaps;

    public MultiLevelCacheSystem() {
        heaps = new PriorityQueue[3];
        for (int i = 0; i < 3; i++) {
            int idx = i;
            heaps[i] = new PriorityQueue<>((a, b) -> {
                int cmp = Double.compare(a.score(cost[idx]), b.score(cost[idx]));
                if (cmp == 0) return Long.compare(a.time, b.time);
                return cmp;
            });
        }
    }

    public String get(int key) {
        if (!map.containsKey(key)) return null;
        CacheEntry entry = map.get(key);
        entry.freq++;
        entry.time = System.nanoTime();
        rebalance(entry);
        return entry.value;
    }

    public void put(int key, String value) {
        if (map.containsKey(key)) {
            CacheEntry entry = map.get(key);
            entry.value = value;
            entry.freq++;
            entry.time = System.nanoTime();
            rebalance(entry);
            return;
        }
        CacheEntry newEntry = new CacheEntry(key, value, 1, System.nanoTime(), 2);
        map.put(key, newEntry);
        heaps[2].offer(newEntry);
        promoteIfNeeded(newEntry);
    }

    private void rebalance(CacheEntry entry) {
        heaps[entry.level].remove(entry);
        heaps[entry.level].offer(entry);
        promoteIfNeeded(entry);
    }

    private void promoteIfNeeded(CacheEntry entry) {
        while (entry.level > 0 && entry.freq / (double) cost[entry.level] > entry.freq / (double) cost[entry.level - 1]) {
            moveEntry(entry, entry.level - 1);
        }
        while (heaps[entry.level].size() > capacity[entry.level]) {
            CacheEntry evict = heaps[entry.level].poll();
            if (entry.level < 2) moveEntry(evict, entry.level + 1);
        }
    }

    private void moveEntry(CacheEntry entry, int newLevel) {
        heaps[entry.level].remove(entry);
        entry.level = newLevel;
        heaps[newLevel].offer(entry);
    }

    public void printState() {
        for (int i = 0; i < 3; i++) {
            List<Integer> keys = new ArrayList<>();
            for (CacheEntry e : heaps[i]) keys.add(e.key);
            System.out.println("L" + (i + 1) + ": " + keys);
        }
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A"); cache.put(2, "B"); cache.put(3, "C");
        cache.printState();
        cache.get(1); cache.get(1); cache.get(2);
        cache.printState();
        cache.put(4, "D"); cache.put(5, "E"); cache.put(6, "F");
        cache.printState();
    }
}
