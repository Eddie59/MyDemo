package spring.lifecycle;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person {

    public Person()
    {
        System.out.println("Person 构造函数");
    }
    private String sex;
    private String girlFriend;
    private String profession;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGirlFriend() {
        return girlFriend;
    }

    public String getProfession() {
        return profession;
    }

    public void setGirlFriend(String girlFriend) {
        this.girlFriend = girlFriend;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public void myInit() {
        System.out.println("my init");
    }

    public void myDestory() {
        System.out.println("my destory");
    }
}
