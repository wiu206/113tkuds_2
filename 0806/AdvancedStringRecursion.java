import java.util.*;

public class AdvancedStringRecursion {

    public static void main(String[] args) {
        System.out.println("字串排列組合：");
        permute("", "abc");

        System.out.println("\n字串匹配：");
        System.out.println("pattern 'lo' in 'hello' 的起始索引：" + match("hello", "lo", 0));

        System.out.println("\n移除重複字元：");
        System.out.println(removeDuplicates("banana", 0, new HashSet<>()));

        System.out.println("\n所有子字串組合：");
        substringCombinations("abc", 0, "");
    }

    public static void permute(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            permute(prefix + remaining.charAt(i), 
                    remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }

    public static int match(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) return -1;
        if (text.substring(index, index + pattern.length()).equals(pattern)) return index;
        return match(text, pattern, index + 1);
    }

    public static String removeDuplicates(String s, int index, Set<Character> seen) {
        if (index == s.length()) return "";
        char c = s.charAt(index);
        if (seen.contains(c)) return removeDuplicates(s, index + 1, seen);
        seen.add(c);
        return c + removeDuplicates(s, index + 1, seen);
    }

    public static void substringCombinations(String s, int index, String current) {
        if (index == s.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }
        substringCombinations(s, index + 1, current + s.charAt(index));
        substringCombinations(s, index + 1, current);
    }
}
