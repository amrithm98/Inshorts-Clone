package com.cet.inshorts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by amrith on 10/6/17.
 */

public interface RestApiInterface {

    @GET("articles")
    Call<List<Article>> getNews();
}
