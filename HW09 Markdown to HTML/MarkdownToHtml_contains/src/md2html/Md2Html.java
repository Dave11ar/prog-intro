package md2html;

import java.lang.*;
import java.io.*;

public class  Md2Html {
    public static void main(String[] args) throws IOException {
            FastScanner sc = new FastScanner(new File(args[0]));
            Parse p = new Parse();
            Writer writer = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(args[1]), "utf8"));
            p.init();

            while (sc.hasNextLine()) {
                String s = sc.nextLine();

                if (s.equals("")) {
                    if (!p.list.isEmpty()) {
                        writer.write(p.start().toString());
                    }
                    while (sc.hasNextLine() && s.equals("")) {
                        s = sc.nextLine();
                    }
                    p.init();
                }

                p.list.add(s);
            }

        writer.write(p.start().toString());
        writer.close();
    }
}
