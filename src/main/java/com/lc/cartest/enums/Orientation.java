package com.lc.cartest.enums;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
* @Description: 方向枚举
* @Author: lc
* @Date: 2020/7/1 0001 16:52
*/
public enum Orientation {
    NORTH("N","NORTH"),
    SOUTH("S","SOUTH"),
    WEST("W","WEST"),
    EAST("E","EAST");
    private String name;
    private String discription;

    Orientation(String name,String discription) {
        this.name = name;
        this.discription = discription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public static Orientation getOrientationByName(String name) {
        Orientation orientation = Arrays.stream(Orientation.values())
                .filter(orientation1 -> StringUtils.equals(name, orientation1.name))
                .findFirst()
                .orElse(null);
        return orientation;
    }
}
