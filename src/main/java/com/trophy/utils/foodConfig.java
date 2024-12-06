package com.trophy.utils;

import net.minecraft.item.Item;

public class foodConfig {
    private int time;
    private float coof;
    private Item itemId;


    public foodConfig(int time, float coof, Item itemId) {

        this.time = time;
        this.coof = coof;
        this.itemId = itemId;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getCoof() {
        return coof;
    }

    public void setCoof(float coof) {
        this.coof = coof;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }
}

