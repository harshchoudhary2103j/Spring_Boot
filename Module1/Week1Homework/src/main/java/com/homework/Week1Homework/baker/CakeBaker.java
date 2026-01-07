package com.homework.Week1Homework.baker;

import com.homework.Week1Homework.frosting.Frosting;
import com.homework.Week1Homework.syrup.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {
    private final Frosting frost;
    private final Syrup syrup;

    public CakeBaker(@Qualifier("cFrost") Frosting frost, @Qualifier("sSyrup") Syrup syrup) {
        this.frost = frost;
        this.syrup = syrup;
    }
    public void bakeCake(){
        System.out.println("Baking cake with: "+ frost.getFrostingType()+" and "+syrup.getSyrupType());

    }
}
