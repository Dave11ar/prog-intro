import java.util.*;
import java.lang.*;
import java.io.*;

class Pairs {
    long key;
    int line;
    
    Pairs (long key_i, int line_i) {
        key = key_i;
        line = line_i;
    }
}

class MyComparator implements Comparator<Pairs> {
    @Override
    public int compare(Pairs a, Pairs b) {
        if (a.key > b.key) {
            return 1;
        }
        if (a.key == b.key) {
            return 0;
        }
        
        return -1;
    }
}

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
    
class FastScanner {
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
    
    FastScanner(InputStream stream) {
        reader = new InputStreamReader(stream);
    }
    
    FastScanner(String s) {
        reader = new StringReader(s);
    }
    
    private String readLine() throws IOException {
        char[] cur_line = new char[1];
        int size = 0;
        int next = reader.read();
        cur_pos = 0;
        
        if (next == -1) {
            buffer = null;
            return null;
        }
        
        while (next != '\n' && next != '\r' && next != -1) {
            if (size == cur_line.length) {
                cur_line = Arrays.copyOf(cur_line, cur_line.length * 2);
            }
            cur_line[size++] = (char)next;
            next = reader.read();
        }
        cur_pos = 0;
        buffer = new String(cur_line, 0, size);
        return buffer;
    }
    
    public String nextLine() throws IOException {
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
    
    public void nextToken() throws IOException {
        if (token != null) {
            return;
        }
        
        while (buffer != null) {
            int l = cur_pos;
            while (l < buffer.length() && Character.isWhitespace(buffer.charAt(l))) {
                l++;
            }
            
            int r = l;
            while (r < buffer.length() && !Character.isWhitespace(buffer.charAt(r))) {
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
    
    public int nextInt() throws IOException {
        nextToken();
        while (token == null && readLine() != null) {
            nextToken();
        }
        
        if (token == null) {
            throw new NoSuchElementException();
        }
        
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
        nextToken();
        while (token == null && readLine() != null) {
            nextToken();
        }
        
        if (token == null) {
            return false;
        }
        
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void closeInput() throws IOException {
        reader.close();
    }
}

public class ReverseSort {
    public static void main(String[] args) throws IOException {
        ArrayList<MyVector> vec = new ArrayList<>();
        FastScanner sc = new FastScanner(System.in);
        int n = 0;
        int mx = 0;
        
        while (sc.hasNextLine()) {
            FastScanner line = new FastScanner(sc.nextLine());
            vec.add(new MyVector());
            
            while (line.hasNextInt()) {
                vec.get(n).push_back(line.nextInt());
            }
            n++;
            line.closeInput();
        }
        sc.closeInput();
        
        List<Pairs> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long curs = 0;
            for (int j = 0; j < vec.get(i).length; j++) {
                curs += vec.get(i).get(j);
            }
            list.add(new Pairs(curs, i));
        }
        
        Collections.sort(list, new MyComparator());
        
        for (int i = list.size() - 1; i >= 0 ; i--) {
            Pairs p = list.get(i);
            Arrays.sort(vec.get(p.line).t, 0, vec.get(p.line).last);
            
            for (int j = vec.get(p.line).last - 1; j >= 0; j--) {
                System.out.print(vec.get(p.line).get(j) + " ");
            }
            System.out.println();
        }
    }
}
