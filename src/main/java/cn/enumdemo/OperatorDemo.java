package cn.enumdemo;

import org.junit.Test;

import java.util.Arrays;

/**
 * OperatorDemo class
 *
 * @author Administrator
 * @date
 */
public class OperatorDemo {
    public static enum Operator {
        eq("等于", "="),
        ne("不等于", "!="),
        gt("大于", ">"),
        ge("大于等于", ">="),
        lt("小于", "<"),
        le("小于等于", "<="),
        isNull("空", "is null"),
        isNotNull("非空", "is not null"),
        in("包含", "in"),
        notIn("不包含", "not in"),
        between("对应SQL的between子句", "between"),
        prefixLike("前缀模糊匹配", "like"),
        prefixNotLike("前缀模糊不匹配", "not like"),
        suffixLike("后缀模糊匹配", "like"),
        suffixNotLike("后缀模糊不匹配", "not like"),
        like("模糊匹配", "like"),
        notLike("不匹配", "not like"),
        custom("自定义默认的", null);

        private final String info;
        private final String symbol;

        Operator(final String info, String symbol) {
            this.info = info;
            this.symbol = symbol;
        }

        public String getInfo() {
            return info;
        }

        public String getSymbol() {
            return symbol;
        }

        public static Operator fromString(String value) {
            return Operator.valueOf(value);
        }

        public static String toStringAllOperator() {
            return Arrays.toString(Operator.values());
        }
    }

    @Test
    public void run()
    {
        Operator operator= Operator.fromString("eq");
        System.out.println(operator.getInfo()+" "+ operator.getSymbol());

        System.out.println(
                Operator.toStringAllOperator()
        );


    }
}
