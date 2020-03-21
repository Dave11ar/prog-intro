package markup;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Text extends elementClass {
    String str;
    
    public void toMarkdown(StringBuilder s) {
        s.append(str);
    }
    
    public void toHtml(StringBuilder s) {
        s.append(str);
    }
    
    Text(String in) {
        str = in;
    }
}
