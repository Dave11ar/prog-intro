import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.Object.*;
import java.util.AbstractMap.*;
import java.util.Map.*;
import java.io.Writer.*;
import java.io.Writer.*;

public class WordStatCount {
    public static boolean word(char ch) {
        return Character.getType(ch) == Character.DASH_PUNCTUATION || Character.isLetter(ch) || ch == '\'';
    }
    
    public static void main(String[] args) {
        Map<String, Integer> hashmap = new LinkedHashMap<>();
        char[] s = new char[1];
        int cursize = 1;
        int sc = 0;
        int t;
        char ch;
        String str;
        
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf8"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))) {
            t = bufferedReader.read();
            
            while (t != -1) {
                ch = (char) t;
                
                if (word(ch)) {
                    if (sc == cursize) {
                        cursize *= 2;
                        s = Arrays.copyOf(s, cursize);
                    }
                    s[sc] = ch;
                    sc++;
                } else {
                    if (sc != 0) {
                        str = String.valueOf(Arrays.copyOf(s, sc)).toLowerCase();
                        
                        if (hashmap.get(str) != null) {
                            hashmap.put(str, hashmap.get(str) + 1);
                        } else {
                            hashmap.put(str, 1);
                        }
                        sc = 0;
                    }
                }
                t = bufferedReader.read();
            }
            bufferedReader.close();
            
            List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(hashmap.entrySet());
            
            Collections.sort(list, new Comparator<Entry<String, Integer>>() {
                public int compare(Entry<String, Integer> left, Entry<String, Integer> right) {
                    return left.getValue().compareTo(right.getValue());
                }
            });
            
            for (int i = 0; i < list.size(); i++) {
                bufferedWriter.write(list.get(i).getKey() + " " + list.get(i).getValue());
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException");
        } catch (IOException e){
            System.err.println("IOException");
        }
    }
}
