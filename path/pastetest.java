package path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
  
public class pastetest {
    public void tower(int n,char s,char m,char e)//n个塔从s经过m最终全部移动到e
    {
        if(n==1)
            move(s,e);
        else
        {
            tower(n-1,s,e,m);
            move(s,e);
            tower(n-1,m,s,e);
        }
    }
    public void move(char s,char e){
        System.out.println("move "+s+" to "+e);
    }
    public static void main(String []args){
        pastetest hnt =new pastetest();
        hnt.tower(5,'A','B','C');
    }
}