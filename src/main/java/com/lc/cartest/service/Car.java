package com.lc.cartest.service;

import com.lc.cartest.ex.DataValidateException;
import com.lc.cartest.ex.NosuchOperationException;
import com.lc.cartest.ex.OutOfParkException;

public interface Car {
    void move(String command) throws DataValidateException, NosuchOperationException, OutOfParkException;

    int getPositionX();

    int getPositionY();

    String getOrientation();

}
