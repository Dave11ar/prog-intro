package md2html;

import java.util.*;
import java.lang.*;

class Swap {
    int end;
    boolean first;
    String s;

    Swap(int end, boolean first, String s) {
        this.end = end;
        this.first = first;
        this.s = s;
    }
}

public class Parse {
    List<String> list = new ArrayList<>();
    StringBuilder s, ans;
    Integer h;
    Set<Character> div = new HashSet<>(List.of('*', '_', '-', '`'));
    Set<Character> solo = new HashSet<>(List.of('<', '>', '&'));

    Map<String, Integer> hash = new HashMap<>();
    Map<String, Swap> hashClass = new HashMap<>();
    Map<Integer, Swap> swapMap = new HashMap<>();
    boolean screen;

    void init() {
        list.clear();
        s = new StringBuilder();
        ans = new StringBuilder();
        h = 0;
        hash.clear();
        hashClass.clear();
        swapMap.clear();
        screen = false;
    }

    StringBuilder start() {
        header();
        ans.append(h == 0 ? "<p>" : ("<h" + h.toString() +">"));
        toStr();

        for (int i = 0; i < s.length(); i++) {
            if (screen) {
                if (s.charAt(i) != 'n') {
                    swapMap.put(i - 1, new Swap(i, true, ""));
                    i++;
                }
                screen = false;
                continue;
            }

            if (s.charAt(i) == '!' && i + 1 < s.length() && s.charAt(i + 1) == '[') {
                int j = i + 2;
                StringBuilder href = new StringBuilder();


                href.append("<img alt='");
                while (s.charAt(j) != ']') {
                    href.append(s.charAt(j));
                    j++;
                }
                j += 2;
                href.append("' src='");
                while (s.charAt(j) != ')') {
                    href.append(s.charAt(j));
                    j++;
                }
                href.append("'>");

                swapMap.put(i, new Swap(j + 1, true, href.toString()));


                i = j - 1;
            } else if (div.contains(s.charAt(i))) {
                String mark = mark(i);

                if(mark.equals("-")) {
                  continue;
                } else if (hash.containsKey(mark)) {
                    swapMap.put(hash.get(mark), hashClass.get(mark));
                    swapMap.put(i, new Swap(i + mark.length(), false, mark));
                    hash.remove(mark);
                    hashClass.remove(mark);
                } else {
                    hash.put(mark, i);
                    hashClass.put(mark, new Swap(i + mark.length(), true, mark));
                }

                if(mark.length() == 2) {
                    i++;
                }
            } else if (solo.contains(s.charAt(i))) {
                swapMap.put(i, new Swap(i + 1, true, s.substring(i, i + 1)));
            } else if (s.charAt(i) == '\\') {
                screen = true;
                continue;
            }

            screen = false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (swapMap.containsKey(i)) {
                ans.append(whatMark(swapMap.get(i)));
                i = swapMap.get(i).end - 1;
                continue;
            }
            ans.append(s.charAt(i));
        }

        ans.deleteCharAt(ans.length() - 1);
        ans.append(h == 0 ? "</p>" : "</h" + h.toString() +">");
        ans.append('\n');
        return ans;
    }

    void header() {
        h = 0;
        while (list.get(0).charAt(h) == '#') {
            h++;
        }
        if (list.get(0).charAt(h) != ' ') {
            h = 0;
        }
    }

    void toStr() {
        for (int j = (h != 0 ? h + 1 : 0); j < list.get(0).length(); j++) {
            s.append(list.get(0).charAt(j));
        }
        s.append('\n');

        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                s.append(list.get(i).charAt(j));
            }
            s.append('\n');
        }
    }

    String mark(int i) {
        if (s.charAt(i) == '`' || s.length() == i + 1 || s.charAt(i + 1) != s.charAt(i)) {
            return s.substring(i, i + 1);
        } else {
            return s.substring(i, i + 2);
        }
    }

    String whatMark(Swap s) {
        switch (s.s) {
            case ("<"):
                return "&lt;";
            case (">"):
                return "&gt;";
            case ("&"):
                return "&amp;";
            case ("*"):
            case ("_"):
                return s.first ? "<em>" : "</em>";
            case ("**"):
            case ("__"):
                return s.first ? "<strong>" : "</strong>";
            case ("--"):
                return s.first ? "<s>" : "</s>";
            case ("`"):
                return s.first ? "<code>" : "</code>";
            default:
                return s.s;
        }
    }
}
