package com.cet.inshorts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by amrith on 10/6/17.
 */

public interface RestApiInterface {

    @GET
    Call<ResponeModel> getNews(@Url String url);
}
