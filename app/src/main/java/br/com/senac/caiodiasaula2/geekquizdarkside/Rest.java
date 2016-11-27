package br.com.senac.caiodiasaula2.geekquizdarkside;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.sql.Time;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joaopaulo on 03/11/16.
 */

public class Rest {

    private static Rest instance = new Rest();
    private Retrofit retrofit;
    public static Rest getInstance() { return instance; };

    //public static final String BASE_URL = "http://tsitomcat2.azurewebsites.net/geekquiz2/rest/";

    public static final String BASE_URL = "http://192.168.0.11:8080/pi/rest/";

    public Rest(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public Retrofit get() { return retrofit; };

}
