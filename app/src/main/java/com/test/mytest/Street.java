package com.test.mytest;

import javax.inject.Inject;

/**
 * Created by admin on 2017-11-20.
 */

public class Street {
    @Inject
    public Street(){}

    public String show(){
        return "人民南路";
    }
}
