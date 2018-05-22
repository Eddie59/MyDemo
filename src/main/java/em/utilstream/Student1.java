package em.utilstream;

/**
 * Student1 class
 *
 * @author Administrator
 * @date
 */
public class Student1 {
    int no;
    String name;
    String sex;
    String address;

    public Student1(int no, String name, String sex) {
        this.no = no;
        this.name = name;
        this.sex = sex;
    }

    public Student1(int no, String name, String sex,String address) {
        this.no = no;
        this.name = name;
        this.sex = sex;
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
