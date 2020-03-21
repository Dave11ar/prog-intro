package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public abstract class listClass extends allClass {
    protected List<listClass> list;
    
    public void toHtml(StringBuilder s) {
        for (listClass a : list) {
            a.toHtml(s); 
        }
    }
    
    public listClass(List<listClass> list) {
        this.list = list;
    }
}

