package com.techexample.example01.service.impl;

import java.util.Collection;
import java.util.List;

import org.jdeferred.DeferredManager;
import org.jdeferred.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.techexample.example01.model.Currency;
import com.techexample.example01.restclient.CoinmarketCapRestApi;
import com.techexample.example01.service.CurrenciesService;
import retrofit2.Response;

/**
 * Currencies service implementation
 */
public class CurrenciesServiceImpl implements CurrenciesService {
    private static final Logger LOG = LoggerFactory.getLogger(CurrenciesServiceImpl.class);

    @Inject
    private CoinmarketCapRestApi api;
    @Inject
    private DeferredManager deferredManager;

    @Override
    public Promise<Collection<Currency>, Throwable, Void> currencies() {

        return deferredManager.when(() -> {
            Response<List<Currency>> response = api.getCurrencies().execute();

            if (response.isSuccessful()) {
                LOG.info("GetCurrencies() Response to api successful, {}", response.body());
                return response.body();
            }
            LOG.error("GetCurrencies() Response to api failed, {}", response.errorBody());
            throw new IllegalStateException(response.message());
        });
    }

    @Override
    public Promise<Collection<Currency>, Throwable, Void> currency(String currencyId) {
        return deferredManager.when(() -> {
            Response<List<Currency>> response = api.getCurrencyById(currencyId).execute();

            if (response.isSuccessful()) {
                LOG.info("GetCurrency() Response to api successful, {}", response.body());
                return response.body();
            }
            LOG.error("GetCurrency() Response to api failed, {}", response.errorBody());
            throw new IllegalStateException(response.message());
        });
    }
}
