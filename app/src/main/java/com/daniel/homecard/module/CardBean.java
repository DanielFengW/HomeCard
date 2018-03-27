package com.daniel.homecard.module;

/**
 * 卡片
 * Created by Daniel on 2018/3/27.
 */

public class CardBean {

    private int id;
    private String name;
    private String desc;
    private int count;

    public CardBean(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public CardBean(int id, String name, String desc, int count) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", count=" + count +
                '}';
    }
}
