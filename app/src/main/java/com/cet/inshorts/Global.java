package com.cet.inshorts;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amrith on 10/6/17.
 */

public class Global {
    public static ArrayList<String> category=new ArrayList<>(Arrays.asList("business", "entertainment", "gaming", "general", "music", "politics", "science-and-nature", "sport", "technology"));
    public static String apiKey="2cf080cea55c4f9d9355efd4a185dcaf";
    public static String url="https://news.api.org/v1/articles?apikey"+apiKey+"&source=the-next-web&sortBy=latest";
}
