package em.enumdemo;

import org.junit.Test;

/**
 * Season class
 *
 * @author Administrator
 * @date
 */
public class Season {

    public enum SeasonEnum
    {
        Spring,
        Summer,
        Fall,
        Winter;
    }

    @Test
    public void run()
    {
        //values列举所有枚举变量
       SeasonEnum[] seasons= SeasonEnum.values();

       for(SeasonEnum season:seasons)
       {
           System.out.println(season);
       }
    }
}
