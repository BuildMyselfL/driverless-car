package com.lc.cartest.service.impl;

import com.lc.cartest.enums.Operation;
import com.lc.cartest.enums.Orientation;
import com.lc.cartest.ex.DataValidateException;
import com.lc.cartest.ex.NosuchOperationException;
import com.lc.cartest.ex.OutOfParkException;
import com.lc.cartest.pojo.Park;
import com.lc.cartest.service.Car;
import com.lc.cartest.util.RingList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: driverless-car
 * @description:
 * @author: lc
 * @create: 2020-07-01 16:39
 **/
@Component
public class DriverlessCarImpl implements Car {

    /**
     * 停车场
     */
    @Autowired
    private Park park;

    /**
     * 水平index
     */
    @Value("${positionX}")
    private Integer positionX;

    /**
     * 垂直index
     */
    @Value("${positionY}")
    private Integer positionY;

    /**
     * 垂直index
     */
    @Value("${direction}")
    private String direction;

    /**
     * 方向
     */
    private Orientation orientation;


    /**
     * 转向环形列表
     */
    private static final RingList<Orientation> orientationRingList = new RingList<>();

    static {
        orientationRingList.add(Orientation.NORTH);
        orientationRingList.add(Orientation.EAST);
        orientationRingList.add(Orientation.SOUTH);
        orientationRingList.add(Orientation.WEST);
    }


    @PostConstruct
    public  void  init (){
        this.orientation = Orientation.getOrientationByName(direction);
    }

    @Override
    public void move(String command) throws DataValidateException, NosuchOperationException, OutOfParkException {
        if (StringUtils.isEmpty(command)) {
            throw new DataValidateException("Operation instruction is empty! Please re-enter!");
        }
        String[] s = command.split(" ");
        if (s.length != 2) {
            throw new DataValidateException("Wrong command! Please enter the correct instruction!");
        }
        String code = s[0];
        Operation operation = Operation.operation(code);
        if (operation == null) {
            throw new NosuchOperationException("Operation instruction does not exist! Only TURN/GO commands are supported!");
        }
        String count = s[1];
        int realCount;
        try {
            realCount = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new DataValidateException("The second operation parameter error, please enter number parameter!");
        }
        int i;

        switch (operation) {
            case GO:
                switch (orientation) {
                    case NORTH:
                        i = this.getPositionY() + realCount;
                        if (i > park.getEdgeWidth()) {
                            throw new OutOfParkException("out of park y length");
                        }
                        System.out.printf("operation : %s,before x: %s ,before y : %s,before orientation: %s , after x : %s ,after y: %s,after orientation:%s \n",
                                command,positionX,positionY,orientation.getDiscription(),positionX,i,orientation.getDiscription());
                        this.positionY = i;
                        break;
                    case SOUTH:
                        i = this.getPositionY() - realCount;
                        if (i <= 0) {
                            throw new OutOfParkException("out of park y length");
                        }
                        System.out.printf("operation : %s,before x: %s ,before y : %s,before orientation: %s , after x : %s ,after y: %s,after orientation:%s \n",
                                command,positionX,positionY,orientation.getDiscription(),positionX,i,orientation.getDiscription());
                        this.positionY = i;
                        break;
                    case WEST:
                        i = this.getPositionX() - realCount;
                        if (i <= 0) {
                            throw new OutOfParkException("out of park x length");
                        }
                        System.out.printf("operation : %s,before x: %s ,before y : %s,before orientation: %s , after x : %s ,after y: %s,after orientation:%s \n",
                                command,positionX,positionY,orientation.getDiscription(),i,positionY,orientation.getDiscription());
                        this.positionX = i;
                        break;
                    case EAST:
                        i = this.getPositionX() + realCount;
                        if (i > park.getSideLength()) {
                            throw new OutOfParkException("out of park x length");
                        }
                        System.out.printf("operation : %s,before x: %s ,before y : %s,before orientation: %s , after x : %s ,after y: %s,after orientation:%s \n",
                                command,positionX,positionY,orientation.getDiscription(),i,positionY,orientation.getDiscription());
                        this.positionX = i;
                        break;
                }
                break;
            case TURN:
                Orientation next = orientationRingList.next(this.orientation, realCount);
                System.out.printf("operation : %s,before x: %s ,before y : %s,before orientation: %s , after x : %s ,after y: %s,after orientation:%s \n",
                        command,positionX,positionY,orientation.getDiscription(),positionX,positionY,next.getDiscription());
                this.orientation = next;
                break;
        }
    }

    @Override
    public int getPositionX() {
        return this.positionX;
    }

    @Override
    public int getPositionY() {
        return this.positionY;
    }

    @Override
    public String getOrientation() {
        return orientation.getName();
    }
}
