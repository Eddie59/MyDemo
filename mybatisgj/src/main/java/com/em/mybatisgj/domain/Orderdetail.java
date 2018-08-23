package com.em.mybatisgj.domain;

import javax.persistence.*;

@Table(name = "orderdetail")
public class Orderdetail extends BaseEntity {
    @Id
    private Integer id;

    /**
     * 订单id
     */
    @Column(name = "orders_id")
    private Integer ordersId;

    /**
     * 商品id
     */
    @Column(name = "items_id")
    private Integer itemsId;

    /**
     * 商品购买数量
     */
    @Column(name = "items_num")
    private Integer itemsNum;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单id
     *
     * @return orders_id - 订单id
     */
    public Integer getOrdersId() {
        return ordersId;
    }

    /**
     * 设置订单id
     *
     * @param ordersId 订单id
     */
    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * 获取商品id
     *
     * @return items_id - 商品id
     */
    public Integer getItemsId() {
        return itemsId;
    }

    /**
     * 设置商品id
     *
     * @param itemsId 商品id
     */
    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    /**
     * 获取商品购买数量
     *
     * @return items_num - 商品购买数量
     */
    public Integer getItemsNum() {
        return itemsNum;
    }

    /**
     * 设置商品购买数量
     *
     * @param itemsNum 商品购买数量
     */
    public void setItemsNum(Integer itemsNum) {
        this.itemsNum = itemsNum;
    }
}