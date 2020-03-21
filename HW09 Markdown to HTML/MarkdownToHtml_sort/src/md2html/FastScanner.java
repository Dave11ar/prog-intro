package md2html;

import java.util.*;
import java.lang.*;
import java.io.*;

public class FastScanner implements AutoCloseable {
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
        if (!hasNextWord()) {
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
        if (!hasNextWord()) {
            return false;
        }

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
