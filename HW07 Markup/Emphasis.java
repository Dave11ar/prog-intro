package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Emphasis extends elementClass {
    public void toMarkdown(StringBuilder s) {
        s.append("*");
        super.toMarkdown(s);
        s.append("*");
    }
    
    public void toHtml(StringBuilder s) {
        s.append("<em>");
        super.toHtml(s);
        s.append("</em>");
    }
    
    public Emphasis(List<elementClass> list) {
        super(list);
    }
}
