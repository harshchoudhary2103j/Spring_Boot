package com.homework.Week1Homework.frosting;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("sFrost")
public class StrawBerryFrost implements Frosting{
    @Override
    public String getFrostingType() {
        return "Strawberry Frosting";
    }
}
