package cn.enumdemo;

import org.junit.Test;

/**
 * Gender class
 *
 * @author Administrator
 * @date
 */
public class Gender {
    /**
     * 枚举里面有两个枚举变量MALE,FEMALE
     * 这两个枚举变量分别代表什么？用name来表示
     */
    public enum GenderEnum
    {
        MALE,FEMALE;
        public String name;
    }
    @Test
    public void run()
    {
        //获取FEMALE变量
        GenderEnum femaleEnum= Enum.valueOf(GenderEnum.class,"FEMALE");

        femaleEnum.name="女";
        System.out.println(femaleEnum+"代表"+ femaleEnum.name);
    }

}
