package example.demo;

import java.lang.reflect.Array;
import java.util.*;

public class Cart {
    String Grocer;
    Dictionary<Integer, String[]> dict;

    Integer i = 0;

    public Cart(String Grocer) {
        this.Grocer = Grocer;
        this.dict = new Hashtable<>();
    }

    public void addItem(String Name, String Price, String count) {
        if(getKey(Name)==-1){
            String[] ItemDetails = {Name,Price, count};
            this.dict.put(i, ItemDetails);
            i++;
        }

    }

    public void removeItem(Integer Name) {
        this.dict.remove(Name);
        i--;
    }

    public void removeall() {
        Enumeration<Integer> keys = dict.keys();
        while (keys.hasMoreElements()) {
            Integer key = keys.nextElement();
            this.dict.remove(key);
        }
        i=0;
    }

    public String[] displayItems() {
        ArrayList<String> items = new ArrayList<String>();
        Enumeration<Integer> var = dict.keys();
        while (var.hasMoreElements()) {
            Integer hi = var.nextElement();

            String item = (dict.get(hi)[0] + " Item Price: " + dict.get(hi)[1] + " Item count: " + dict.get(hi)[2]);
            items.add(item);
        }
        String k[] = (items.toArray(new String[items.size()]) != null) ? items.toArray(new String[items.size()]) : new String[]{" "};

        return k;
    }

    public void getKeys() {
        ArrayList<String> items = new ArrayList<String>();
        Enumeration<Integer> var = dict.keys();
        while (var.hasMoreElements()) {
            Integer hi = var.nextElement();
        }
    }
    public Integer getKey(String name){
        if(dict.isEmpty()){
            return -1;
        }
        Enumeration<Integer> keys = this.dict.keys();
        Integer hi = keys.nextElement();
        while (keys.hasMoreElements() && !dict.get(hi)[0].contains(name)) {
            hi = keys.nextElement();
        }
        if(dict.get(hi)[0].contains(name)){
            return hi;
        }
        else{
            return -1;
        }
    }
}

