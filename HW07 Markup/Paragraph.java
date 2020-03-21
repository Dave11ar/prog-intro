package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Paragraph extends allClass {
    protected List<allClass> list;

    public void toMarkdown (StringBuilder s) {
        for (allClass a : list) {
            a.toMarkdown(s); 
        }
    }
    
    public void toHtml(StringBuilder s) {
        for (allClass a : list) {
            a.toHtml(s); 
        }
    }
    
    public Paragraph(List<allClass> list) {
        this.list = list;
    }
}
