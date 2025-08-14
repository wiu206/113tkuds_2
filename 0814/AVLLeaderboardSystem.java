import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        String player;
        int score, height, size; // size = 子樹節點數
        Node left, right;

        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }

        void updateHeightAndSize() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            height = Math.max(lh, rh) + 1;

            int ls = (left == null) ? 0 : left.size;
            int rs = (right == null) ? 0 : right.size;
            size = ls + rs + 1;
        }

        int getBalance() {
            int lh = (left == null) ? 0 : left.height;
            int rh = (right == null) ? 0 : right.height;
            return lh - rh;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>();

    // 添加或更新玩家分數
    public void addOrUpdatePlayer(String player, int score) {
        if (playerScores.containsKey(player)) {
            root = deleteRec(root, player, playerScores.get(player));
        }
        playerScores.put(player, score);
        root = insertRec(root, player, score);
    }

    // 插入節點
    private Node insertRec(Node node, String player, int score) {
        if (node == null) return new Node(player, score);

        // 排序依分數高到低，分數相同按名稱
        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            node.left = insertRec(node.left, player, score);
        } else {
            node.right = insertRec(node.right, player, score);
        }

        node.updateHeightAndSize();
        return rebalance(node, player, score);
    }

    // 刪除節點
    private Node deleteRec(Node node, String player, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            node.left = deleteRec(node.left, player, score);
        } else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0)) {
            node.right = deleteRec(node.right, player, score);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node successor = findMin(node.right);
                node.player = successor.player;
                node.score = successor.score;
                node.right = deleteRec(node.right, successor.player, successor.score);
            }
        }

        if (node != null) {
            node.updateHeightAndSize();
            node = rebalanceAfterDelete(node);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // 平衡處理
    private Node rebalance(Node node, String player, int score) {
        int balance = node.getBalance();

        if (balance > 1 && (score > node.left.score || (score == node.left.score && player.compareTo(node.left.player) < 0)))
            return rightRotate(node);

        if (balance < -1 && (score < node.right.score || (score == node.right.score && player.compareTo(node.right.player) > 0)))
            return leftRotate(node);

        if (balance > 1 && (score < node.left.score || (score == node.left.score && player.compareTo(node.left.player) > 0))) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && (score > node.right.score || (score == node.right.score && player.compareTo(node.right.player) < 0))) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rebalanceAfterDelete(Node node) {
        int balance = node.getBalance();

        if (balance > 1 && node.left.getBalance() >= 0) return rightRotate(node);
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right.getBalance() <= 0) return leftRotate(node);
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 左旋
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeightAndSize();
        y.updateHeightAndSize();

        return y;
    }

    // 右旋
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeightAndSize();
        x.updateHeightAndSize();

        return x;
    }

    // 查詢玩家排名（排名1是最高分）
    public int getPlayerRank(String player) {
        if (!playerScores.containsKey(player)) return -1;
        return getRank(root, player, playerScores.get(player)) + 1;
    }

    private int getRank(Node node, String player, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            return getRank(node.left, player, score);
        } else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0)) {
            int leftSize = (node.left != null) ? node.left.size : 0;
            return leftSize + 1 + getRank(node.right, player, score);
        } else {
            return (node.left != null) ? node.left.size : 0;
        }
    }

    // 查詢前 K 名玩家
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        getTopKRec(root, result, k);
        return result;
    }

    private void getTopKRec(Node node, List<String> result, int k) {
        if (node == null || result.size() >= k) return;
        getTopKRec(node.left, result, k);
        if (result.size() < k) {
            result.add(node.player + " (" + node.score + ")");
        }
        getTopKRec(node.right, result, k);
    }

    // 測試
    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addOrUpdatePlayer("Alice", 50);
        leaderboard.addOrUpdatePlayer("Bob", 70);
        leaderboard.addOrUpdatePlayer("Charlie", 60);
        leaderboard.addOrUpdatePlayer("David", 80);
        leaderboard.addOrUpdatePlayer("Eve", 90);

        System.out.println("前3名玩家: " + leaderboard.getTopK(3));
        System.out.println("Bob 的排名: " + leaderboard.getPlayerRank("Bob"));

        leaderboard.addOrUpdatePlayer("Bob", 95); // 更新分數
        System.out.println("更新後前3名玩家: " + leaderboard.getTopK(3));
        System.out.println("Bob 的新排名: " + leaderboard.getPlayerRank("Bob"));
    }
}
