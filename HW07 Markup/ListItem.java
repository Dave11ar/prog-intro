package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ListItem extends allClass {
    protected List<listClass> list;
    
    public void toHtml(StringBuilder s) {
        s.append("<li>");
        for (listClass a : list) {
            a.toHtml(s); 
        }
        s.append("</li>");
    }
    
    public ListItem(List<listClass> list) {
        this.list = list;
    }
}
