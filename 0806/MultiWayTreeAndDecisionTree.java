import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class MultiNode {
        String value;
        List<MultiNode> children = new ArrayList<>();
        MultiNode(String value) { this.value = value; }
    }

    public static void main(String[] args) {
        MultiNode root = new MultiNode("A");
        MultiNode b = new MultiNode("B");
        MultiNode c = new MultiNode("C");
        MultiNode d = new MultiNode("D");
        MultiNode e = new MultiNode("E");
        MultiNode f = new MultiNode("F");
        MultiNode g = new MultiNode("G");

        root.children.add(b);
        root.children.add(c);
        root.children.add(d);
        b.children.add(e);
        b.children.add(f);
        d.children.add(g);

        System.out.println("深度優先走訪：");
        dfs(root);

        System.out.println("\n廣度優先走訪：");
        bfs(root);

        System.out.println("\n節點度數：");
        printDegrees(root);

        System.out.println("\n樹的高度：" + getHeight(root));

        System.out.println("\n簡單決策樹模擬：");
        simulateGuessGame();
    }

    public static void dfs(MultiNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        for (MultiNode child : node.children) {
            dfs(child);
        }
    }

    public static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiNode node = queue.poll();
            System.out.print(node.value + " ");
            for (MultiNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    public static int getHeight(MultiNode node) {
        if (node == null || node.children.isEmpty()) return 1;
        int max = 0;
        for (MultiNode child : node.children) {
            max = Math.max(max, getHeight(child));
        }
        return max + 1;
    }

    public static void printDegrees(MultiNode node) {
        if (node == null) return;
        System.out.println(node.value + " 的度數：" + node.children.size());
        for (MultiNode child : node.children) {
            printDegrees(child);
        }
    }

    public static void simulateGuessGame() {
        DecisionNode root = new DecisionNode("你想的數字大於 50 嗎？");
        root.yes = new DecisionNode("你想的數字是 75 嗎？");
        root.no = new DecisionNode("你想的數字是 25 嗎？");

        Scanner scanner = new Scanner(System.in);
        DecisionNode curr = root;
        while (curr != null) {
            System.out.println(curr.question);
            if (curr.yes == null && curr.no == null) {
                break;
            }
            String ans = scanner.nextLine().trim().toLowerCase();
            if (ans.equals("是") || ans.equals("y")) {
                curr = curr.yes;
            } else {
                curr = curr.no;
            }
        }
        System.out.println("猜測結束！");
    }

    static class DecisionNode {
        String question;
        DecisionNode yes, no;
        DecisionNode(String question) { this.question = question; }
    }
}
