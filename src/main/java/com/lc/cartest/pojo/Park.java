package com.lc.cartest.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: driverless-car
 * @description: 停车场
 * @author: lc
 * @create: 2020-07-01 17:01
 **/
@Component
public class Park {

    @Value("${sideLength}")
    private Integer sideLength;

    @Value("${edgeWidth}")
    private Integer edgeWidth;

    public Integer getSideLength() {
        return sideLength;
    }

    public void setSideLength(Integer sideLength) {
        this.sideLength = sideLength;
    }

    public Integer getEdgeWidth() {
        return edgeWidth;
    }

    public void setEdgeWidth(Integer edgeWidth) {
        this.edgeWidth = edgeWidth;
    }
}
