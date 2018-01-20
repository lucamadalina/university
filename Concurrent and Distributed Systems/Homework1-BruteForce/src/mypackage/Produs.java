package mypackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by madalina.luca on 10/30/2017.
 */
public class Produs {

    private String name;
    private String id;
    private String link;
    private String price;
    private Map<String, String> list = new HashMap<>();

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Produs(String name, String id, String url, String price) {
        this.name = name;
        this.id = id;
        this.link = url;
        this.price = price;
        this.list.put(url, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getList() {
        return list;
    }

    public void setList(String link, String price) {
        this.list.put(link, price);
    }



}
