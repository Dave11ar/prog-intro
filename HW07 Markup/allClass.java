package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public abstract class allClass{
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
}
