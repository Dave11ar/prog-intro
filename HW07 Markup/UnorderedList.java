package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class UnorderedList extends allClass {
    public void toHtml(StringBuilder s) {
        s.append("<ul>");
        super.toHtml(s);
        s.append("</ul>");
    }
    
    public UnorderedList(List<listClass> list) {
        super(list);
    }
}
