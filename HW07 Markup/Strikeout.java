package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Strikeout extends elementClass {
    public void toMarkdown (StringBuilder s) {
        s.append("~");
        super.toMarkdown(s);
        s.append("~");
    }
    
    public void toHtml(StringBuilder s) {
        s.append("<s>");
        super.toHtml(s);
        s.append("</s>");
    }
    
    public Strikeout(List<elementClass> list) {
        super(list);
    }
}
