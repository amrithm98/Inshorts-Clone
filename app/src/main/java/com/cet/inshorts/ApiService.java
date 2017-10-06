package com.cet.inshorts;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amrith on 10/6/17.
 */

public class ApiService {

    public static final String  HOST_URL = Global.url ;
    public static final String  NODE_PORT = "";
    public static final String  BASE_URL = HOST_URL ;

    public static RestApiInterface getService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestApiInterface.class);

    }
}
