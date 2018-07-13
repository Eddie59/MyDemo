package cn.javaio.objectinputstream.objs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TV class
 *
 * @author Administrator
 * @date
 */
public class TV implements Serializable {
    private int price;
    private double weigth;
    private String describe;
    private boolean isNice;
    List<List<String>> list;

    public TV() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList<String>());
        }
    }

    public List<List<String>> getList() {
        return list;
    }

    public void setList(List<List<String>> list) {
        this.list = list;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isNice() {
        return isNice;
    }

    public void setNice(boolean isNice) {
        this.isNice = isNice;
    }

}
