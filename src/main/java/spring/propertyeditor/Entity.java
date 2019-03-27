package spring.propertyeditor;

import java.util.Date;

public class Entity {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



}