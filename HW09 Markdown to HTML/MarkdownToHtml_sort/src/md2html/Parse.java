package md2html;

import java.util.*;
import java.lang.*;

class Swap {
    int start;
    int end;
    boolean first;
    String s;

    Swap(int start, int end, boolean first, String s) {
        this.start = start;
        this.end = end;
        this.first = first;
        this.s = s;
    }
}

class MyComparator implements Comparator<Swap> {
    @Override
    public int compare(Swap a, Swap b) {
        if (a.start > b.start) {
            return 1;
        }
        if (a.start == b.start) {
            return 0;
        }

        return -1;
    }
}

public class Parse {
    List<String> list = new ArrayList<>();
    StringBuilder s, ans;
    Integer h;
    Set<Character> div = new HashSet<>(List.of('*', '_', '-'));
    Map<String, Integer> hash = new HashMap<>();
    Map<String, Swap> hashClass = new HashMap<>();
    List<Swap> swapList = new ArrayList<>();
    boolean screen;

    void init() {
        list.clear();
        s = new StringBuilder();
        ans = new StringBuilder();
        h = 0;
        hash.clear();
        hashClass.clear();
        swapList.clear();
        screen = false;
    }

    StringBuilder start() {
        header();
        ans.append(h == 0 ? "<p>" : ("<h" + h.toString() +">"));
        toStr();

        for (int i = 0; i < s.length(); i++) {
            if (screen) {
                if (s.charAt(i) != 'n') {
                    swapList.add(new Swap(i - 1, i, true, ""));
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

                swapList.add(new Swap(i, j + 1, true, href.toString()));


                i = j - 1;
            } else if (div.contains(s.charAt(i))) {
                String mark = mark(i);

                if(mark.equals("-")) {
                  continue;
                } else if (hash.containsKey(mark)) {
                    swapList.add(hashClass.get(mark));
                    swapList.add(new Swap(i, i + mark.length(), false, mark));
                    hash.remove(mark);
                    hashClass.remove(mark);
                } else {
                    hash.put(mark, i);
                    hashClass.put(mark, new Swap(i, i + mark.length(), true, mark));
                }

                if(mark.length() == 2) {
                    i++;
                }
            } else if (s.charAt(i) == '\\') {
                screen = true;
                continue;
            } else if(s.charAt(i) == '`') {
                if (hash.containsKey("`")) {
                    swapList.add(hashClass.get("`"));
                    swapList.add(new Swap(i, i + 1, false,"`" ));
                    hash.remove("`");
                    hashClass.remove("`");
                } else {
                    hash.put("`", i);
                    hashClass.put("`", new Swap(i, i + 1, true,"`" ));
                }
            } if (s.charAt(i) == '<') {
                swapList.add(new Swap(i, i + 1, true,"<" ));
            } else if (s.charAt(i) == '>') {
                swapList.add(new Swap(i, i + 1, true,">" ));
            } else if (s.charAt(i) == '&') {
                swapList.add(new Swap(i, i + 1, true,"&" ));
            }

            screen = false;
        }


        int g = 0;

        Collections.sort(swapList, new MyComparator());

        for (int i = 0; i < s.length(); i++) {
            if (g < swapList.size() && i == swapList.get(g).start) {
                ans.append(whatMark(swapList.get(g)));
                i = swapList.get(g).end - 1;
                g++;
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
        if (s.length() == i + 1 || s.charAt(i + 1) != s.charAt(i)) {
            return new String(s.substring(i, i + 1));
        } else {
            return new String(s.substring(i, i + 2));
        }
    }

    String whatMark(Swap s) {
        switch (s.s) {
            case ("<"):
                return "&lt;";
            case (">"):
                return "&gt";
            case ("&"):
                return "&amp";
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
