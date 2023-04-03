import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Enumeration;

public class Cart {
    String Grocer;
    Dictionary<String, float[]> dict;
    public Cart(String Grocer) {
        this.Grocer = Grocer;
        this.dict = new Hashtable<>();
    }

    public void addItem(String Name, float Price, float count )
    {
        float[] ItemDetails = {Price, count};
        this.dict.put(Name,ItemDetails);
    }

    public void removeItem(String Name)
    {
        this.dict.remove(Name);
    }
    public void displayItems() {
        Enumeration<String> keys = dict.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println("Item Name: " + key + " Item Price: " + dict.get(key)[0] + " Item count: " + dict.get(key)[1]);
        }
    }
}
