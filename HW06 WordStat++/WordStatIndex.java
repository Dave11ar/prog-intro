import java.util.*;
import java.lang.*;
import java.io.*;

class MyVector {
    int[] t;
    int last;
    int length;

    void exp() {
        length *= 2;
        t = Arrays.copyOf(t, length);
    }

    MyVector() {
        t = new int[1];
        length = 1;
        last = 0;
    }

    int get(int n) {
        return t[n];
    }
    
    void push_back(int x) {
        if (last == length) {
            exp();
        }
        
        t[last]  = x;
        last++;
    }
}

class FastScanner implements AutoCloseable{
    private Reader reader;
    private String buffer;
    private String token;
    private int cur_pos;
    private int r_token;
    
    FastScanner(File file) throws IOException {
        try {
            reader = new InputStreamReader(new FileInputStream(file), "utf8");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    FastScanner(InputStream stream) throws IOException {
        reader = new InputStreamReader(stream);
    }
    
    FastScanner(String s) throws IOException {
        reader = new StringReader(s);
    }
    
    private String readLine() throws IOException {
        StringBuilder str = new StringBuilder();
        int next = reader.read();
        cur_pos = 0;
        
        if (next == -1) {
            buffer = null;
            return null;
        }
        
        while (next != '\n' && next != '\r' && next != -1) {
            str.append((char)next);
            next = reader.read();
        }
        cur_pos = 0;
        buffer = str.toString();
        return buffer;
    }
    
    public String nextLine() throws IOException {
        token = null;
        if (buffer != null || readLine() != null) {
            String tmp = buffer.substring(cur_pos, buffer.length());
            buffer = null;
            return tmp;
        }
        
        return null;
    }
    
    public boolean hasNextLine() throws IOException {
        if (buffer != null || readLine() != null) {
            return true;
        } 
        
        return false;
    }
    
    public boolean word(char ch) {
        return Character.getType(ch) == Character.DASH_PUNCTUATION || Character.isLetter(ch) || ch == '\'';
    }
    
    public void nextToken() throws IOException {
        if (token != null) {
            return;
        }
        
        while (buffer != null) {
            int l = cur_pos;
            while (l < buffer.length() && !word(buffer.charAt(l))) {
                l++;
            }
            
            int r = l;
            while (r < buffer.length() && word(buffer.charAt(r))) {
                r++;
            }
            
            if (l == r) {
                readLine();
            } else {
                token = buffer.substring(l, r);
                r_token = r;
                return;
            }
        }
    }
    
    public boolean hasNextWord() throws IOException {
        nextToken();
        while (token == null && readLine() != null) {
            nextToken();
        }
        
        if (token == null){
            return false;
        } else {
            return true;
        }
    }
    
    public String nextWord() throws IOException {
        nextToken();
        while (token == null && readLine() != null) {
            nextToken();
        }
        
        if (token == null){
            throw new NoSuchElementException();
        } else {
            String str = token + "";
            cur_pos = r_token;
            token = null;
            return str;
        }
    }
    
    public int nextInt() throws IOException {
        nextWord();
        
        try {
            int num = Integer.parseInt(token);
            cur_pos = r_token;
            token = null;
            return num;
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
        
    }
    
    public boolean hasNextInt() throws IOException {
        hasNextWord();
        
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void close() throws IOException {
        reader.close();
    }
}

public class WordStatIndex {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> hashmap = new LinkedHashMap<>();
        char[] s = new char[1];
        int number = 1;
        
        ArrayList<MyVector> vec = new ArrayList<>();
        FastScanner sc = new FastScanner(new File(args[0]));
        
        while (sc.hasNextLine()) {
            FastScanner line = new FastScanner(sc.nextLine().toLowerCase());
            String str;
            
            while (line.hasNextWord()) {
                str = line.nextWord();
                
                if (hashmap.get(str) == null) {
                    hashmap.put(str, vec.size());
                    vec.add(new MyVector());
                }
                vec.get(hashmap.get(str)).push_back(number);
                number++;
            }
        }
        
        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));){
            for (Map.Entry<String, Integer> entry : hashmap.entrySet()) {
                bufferedWriter.write(entry.getKey());
                bufferedWriter.write(" ");
                bufferedWriter.write(vec.get(entry.getValue()).last + "");
                
                for (int i = 0; i < vec.get(entry.getValue()).last; i++) {
                    bufferedWriter.write(" ");
                    bufferedWriter.write(vec.get(entry.getValue()).get(i) + "");
                }
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
