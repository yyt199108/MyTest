package com.test.mytest;

import javax.inject.Inject;

/**
 * Created by admin on 2017-11-20.
 */

public class Province {
    @Inject
    public City city;

    @Inject
    public Province(City city) {
        this.city = city;
    }

    public String showAddress() {
        return "四川省" + city.show();
    }

}
