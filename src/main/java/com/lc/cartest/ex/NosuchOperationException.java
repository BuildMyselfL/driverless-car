package com.lc.cartest.ex;

/**
* @Description: 没有对应的操作异常
* @Author: lc
* @Date: 2020/7/1 22:15
*/
public class NosuchOperationException extends Exception {

    public NosuchOperationException(String message) {
        super(message);
    }
}
