package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class OrderedList extends listClass {
    public void toHtml(StringBuilder s) {
        s.append("<ol>");
        super.toHtml(s);
        s.append("</ol>");
    }
    
    public OrderedList(List<listClass> list) {
        super(list);
    }
}
