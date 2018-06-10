package cn.utilstream;


/**
 * Student class
 *
 * @author Administrator
 * @date
 */
public class Student {
    int no;
    String name;
    String sex;
    float height;
    String address;


    public Student(int no, String name, String sex, float height,String address) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public float getHeight() {
        return height;
    }

    public int getNo() {
        return no;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
