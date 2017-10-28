package file;


import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Dictionary {
    private Set<String> dict;
    private String [] list;
    String headers;

    public Dictionary(){
        dict = new HashSet<String>();
        headers ="";
    }

    public Set<String> getDict(){
        return dict;
    }
    public void setDict(Set<String> copy){
        dict = copy;
    }

    public boolean put(String word){
        if(!dict.contains(word)){
            dict.add(word);
            return true;
        }
        else {
            return false;
        }
    }
    public void setList(){
        list = dict.toArray(new String[dict.size()]);
        setHeaders();
    }
    public String[] getList(){ return list; }

    public void sortList(){
        Arrays.sort(list);
    }

    public void printList(){
        sortList();
        for (String word:list) {
            System.out.print(word+" ");
        }
    }
    public void setHeaders(){
        sortList();
        int count = 0;
        for (String word:list) {
            if(count >0 ){
                headers=headers+","+word;

            }
            else{
                headers = word;
            }


            count++;
        }
    }
    public String getHeaders(){
        return headers;
    }
}
