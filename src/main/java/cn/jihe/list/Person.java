package cn.jihe.list;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person {
    private int cardId;
    private String name;
    private int age;

    public Person(){}

    public Person(int cardId,String name,int age){
        this.cardId=cardId;
        this.name=name;
        this.age=age;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {

        if(this==obj){
            return true;
        }
        Person p=(Person)obj;
        if(p==null){
            return false;
        }
        return this.getCardId()==p.getCardId();
    }
}
