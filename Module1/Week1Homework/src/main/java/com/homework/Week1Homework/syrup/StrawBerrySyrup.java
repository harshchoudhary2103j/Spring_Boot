package com.homework.Week1Homework.syrup;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sSyrup")
public class StrawBerrySyrup implements Syrup{
    @Override
    public String getSyrupType() {
        return "StrawBerry syrup";
    }
}
