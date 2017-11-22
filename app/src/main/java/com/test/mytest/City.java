package com.test.mytest;

import javax.inject.Inject;

/**
 * Created by admin on 2017-11-20.
 */

public class City {
//    @Inject
    public Street street;
//    @Inject
//    public City(Street street) {
//        this.street = street;
//    }

    @Inject
    public City() {
        this.street = new Street();
    }

    public String show() {
        return "成都市" + street.show();
    }

}
