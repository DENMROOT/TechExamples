package com.techexample.example01.service;

import java.util.Collection;

import org.jdeferred.Promise;

import com.techexample.example01.model.Currency;

/**
 * Main coinmarketcap currencies service
 */
public interface CurrenciesService {
    Promise<Collection<Currency>, Throwable, Void> currencies();

    Promise<Collection<Currency>, Throwable, Void> currency(String currencyId);
}
