package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public abstract class elementClass extends allClass {
    protected List<elementClass> list;
    
    public void toMarkdown (StringBuilder s) {
        for (elementClass a : list) {
            a.toMarkdown(s); 
        }
    }
    
    public void toHtml(StringBuilder s) {
        for (elementClass a : list) {
            a.toHtml(s); 
        }
    }
    
    public elementClass(List<elementClass> list) {
        this.list = list;
    }
}

