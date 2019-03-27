package cn.builderpattern.item;

import cn.builderpattern.pack.Packing;
import cn.builderpattern.pack.Wrapper;

/**
 * 汉堡
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}