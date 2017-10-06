package com.cet.inshorts;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amrith on 10/6/17.
 */

public class Global
{
    public static ArrayList<String> category=new ArrayList<>(Arrays.asList("business", "entertainment", "gaming", "general", "music", "politics", "science-and-nature", "sport", "technology"));

    public static String apiKey="2cf080cea55c4f9d9355efd4a185dcaf";

    public static String url="https://newsapi.org/v1/";

    public static String tag="articles?apikey\"+apiKey+\"&source=the-next-web&sortBy=latest/";

    public static String defaultUrl="https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=2cf080cea55c4f9d9355efd4a185dcaf&category=technology/";

    public static String defaultGet="articles?source=the-next-web&sortBy=latest&apiKey=2cf080cea55c4f9d9355efd4a185dcaf&category=technology";
}
