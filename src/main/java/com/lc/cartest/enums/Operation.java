package com.lc.cartest.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @program: driverless-car
 * @description: 操作指令
 * @author: lc
 * @create: 2020-07-01 21:43
 **/
public enum Operation {
    TURN("TURN","顺时针转向"),
    GO("GO","直行");
    private String code;

    private String description;

    Operation(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Operation operation(String code){
        Operation operation1 = Arrays.stream(Operation.values())
                .filter(operation -> StringUtils.equals(code, operation.getCode()))
                .findFirst()
                .orElse(null);
        return operation1;
    }
}
