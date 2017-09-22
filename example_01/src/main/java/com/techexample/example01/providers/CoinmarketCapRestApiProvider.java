package com.techexample.example01.providers;

import static com.techexample.example01.AppConstants.COINMARKETCAP_API_URL_KEY;
import static com.techexample.example01.AppConstants.COINMARKETCAP_API_VERSION;

import javax.inject.Inject;
import javax.inject.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techexample.example01.restclient.CoinmarketCapRestApi;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Denys_Makarov on 9/22/2017.
 */
public class CoinmarketCapRestApiProvider implements Provider<CoinmarketCapRestApi> {

    private String apiUrl = COINMARKETCAP_API_URL_KEY + "/" + COINMARKETCAP_API_VERSION + "/";

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public CoinmarketCapRestApi get() {
        return new Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(CoinmarketCapRestApi.class);
    }

}
