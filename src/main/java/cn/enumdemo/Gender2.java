package cn.enumdemo;

import org.junit.Test;

/**
 * Gender2 class
 *
 * @author Administrator
 * @date
 */
public class Gender2 {
    public enum Gender {
        MALE("男"),
        FEMALE("女");

        private final String name;

        private Gender(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void run() {
        System.out.println(Gender.valueOf("MALE"));

        Gender gender = Gender.FEMALE;
        System.out.println(gender.getName());

        System.out.println(Gender.values());

    }

}
