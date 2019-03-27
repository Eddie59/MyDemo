package cn.builderpattern.item;

import cn.builderpattern.pack.Packing;

/**
 * 抽象一个食物
 */
public interface Item {
    /**
     * 食物名
     * @return
     */
    public String name();

    /**
     * 食物包装
     * @return
     */
    public Packing packing();

    /**
     * 食物价格
     * @return
     */
    public float price();
}
