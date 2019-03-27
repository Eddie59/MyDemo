package cn.builderpattern.item;

import cn.builderpattern.pack.Bottle;
import cn.builderpattern.pack.Packing;

/**
 * 冷饮
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}