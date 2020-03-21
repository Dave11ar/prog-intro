package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Strong extends elementClass {
    public void toMarkdown (StringBuilder s) {
        s.append("__");
        super.toMarkdown(s);
        s.append("__");
    }
    
    public void toHtml(StringBuilder s) {
        s.append("<strong>");
        super.toHtml(s);
        s.append("</strong>");
    }
    
    public Strong(List<elementClass> list) {
        super(list);
    }
}
