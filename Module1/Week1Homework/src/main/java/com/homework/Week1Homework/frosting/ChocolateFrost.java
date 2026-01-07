package com.homework.Week1Homework.frosting;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cFrost")
public class ChocolateFrost implements Frosting{
    @Override
    public String getFrostingType() {
        return "Chocolate Frost";
    }
}
