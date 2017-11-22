package com.test.mytest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-20.
 */
@Module
public class BeanModule {
    @Provides
    Street provideStreet(){
        return new Street();
    }

    @Provides
    City provideCity(Street street){
        return new City();
    }

    @Provides
    Province provideProvince(City city){
        return new Province(city);
    }
}
