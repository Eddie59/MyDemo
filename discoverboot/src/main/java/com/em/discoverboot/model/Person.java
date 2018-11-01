package com.em.discoverboot.model;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person {
    private String cardId;
    private String name;
    private int sex;

    public Person(){
    }

    public Person(String cardId,String name,int sex){
        this.cardId=cardId;
        this.name=name;
        this.sex=sex;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }
}
