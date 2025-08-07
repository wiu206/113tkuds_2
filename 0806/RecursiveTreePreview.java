import java.util.*;

public class RecursiveTreePreview {

    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.files.add("a.txt");
        root.files.add("b.txt");

        Folder sub1 = new Folder("sub1");
        sub1.files.add("c.txt");

        Folder sub2 = new Folder("sub2");
        sub2.files.add("d.txt");
        Folder sub3 = new Folder("sub3");
        sub3.files.add("e.txt");
        sub2.subfolders.add(sub3);

        root.subfolders.add(sub1);
        root.subfolders.add(sub2);

        System.out.println("總檔案數：" + countFiles(root));

        MenuItem menu = new MenuItem("主選單");
        menu.children.add(new MenuItem("檔案"));
        MenuItem 編輯 = new MenuItem("編輯");
        編輯.children.add(new MenuItem("剪下"));
        編輯.children.add(new MenuItem("貼上"));
        menu.children.add(編輯);
        System.out.println("\n選單結構：");
        printMenu(menu, 0);

        List<Object> nested = Arrays.asList(1, Arrays.asList(2, 3), Arrays.asList(Arrays.asList(4), 5));
        System.out.println("\n展平巢狀陣列：" + flatten(nested));

        List<Object> depthTest = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4))));
        System.out.println("\n最大深度：" + maxDepth(depthTest));
    }

    static class Folder {
        String name;
        List<String> files = new ArrayList<>();
        List<Folder> subfolders = new ArrayList<>();
        Folder(String name) { this.name = name; }
    }

    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder sub : folder.subfolders) {
            count += countFiles(sub);
        }
        return count;
    }

    static class MenuItem {
        String title;
        List<MenuItem> children = new ArrayList<>();
        MenuItem(String title) { this.title = title; }
    }

    public static void printMenu(MenuItem item, int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("- " + item.title);
        for (MenuItem child : item.children) {
            printMenu(child, level + 1);
        }
    }

    public static List<Integer> flatten(List<Object> nested) {
        List<Integer> result = new ArrayList<>();
        for (Object obj : nested) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof List) {
                result.addAll(flatten((List<Object>) obj));
            }
        }
        return result;
    }

    public static int maxDepth(List<Object> nested) {
        int max = 1;
        for (Object obj : nested) {
            if (obj instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<Object>) obj));
            }
        }
        return max;
    }
}
