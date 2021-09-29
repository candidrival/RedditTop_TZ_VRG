package com.candidrival.reddittop.network;

import com.candidrival.reddittop.apicommon.BaseConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit newInstance(){

        return new Retrofit.Builder()
                .baseUrl(BaseConstants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
