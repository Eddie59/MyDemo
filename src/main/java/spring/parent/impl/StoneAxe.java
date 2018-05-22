package spring.parent.impl;

import spring.parent.Axe;

/**
 * StoneAxe class
 *
 * @author Administrator
 * @date
 */
public class StoneAxe implements Axe {
    @Override
    public String chop() {
        return "石斧砍柴好慢";
    }
}
