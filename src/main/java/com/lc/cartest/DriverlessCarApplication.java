package com.lc.cartest;

import com.lc.cartest.ex.DataValidateException;
import com.lc.cartest.ex.NosuchOperationException;
import com.lc.cartest.ex.OutOfParkException;
import com.lc.cartest.service.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @program: driverless-car
 * @description:
 * @author: lc
 * @create: 2020-07-01 17:20
 **/
@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class DriverlessCarApplication implements CommandLineRunner {

    @Autowired
    private Car car;

    public static void main(String[] args) {
        SpringApplication.run(DriverlessCarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter instructions (for example : TURN 3 or GO 1) :");
            try {
                car.move(input.nextLine());
            } catch (DataValidateException e) {
                System.out.printf("Exception : %s \n",e.getMessage());
                continue;
            } catch (NosuchOperationException e) {
                System.out.printf("Exception : %s \n",e.getMessage());
                continue;
            } catch (OutOfParkException e) {
                throw e;
            }
        }
    }
}
