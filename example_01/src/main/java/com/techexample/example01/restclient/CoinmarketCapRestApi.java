package com.techexample.example01.restclient;

import java.util.List;

import com.techexample.example01.model.Currency;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * CoinmarketCap REST Api
 */
public interface CoinmarketCapRestApi {

    @GET("ticker")
    Call<List<Currency>> getCurrencies();

    @GET("ticker/{id}")
    Call<List<Currency>> getCurrencyById(@Path("id") String currencyId);
}
