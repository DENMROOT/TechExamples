package com.techexample.example01.controller;

import static com.techexample.example01.model.State.READY;
import static com.techexample.example01.model.State.RUNNING;

import com.google.inject.Inject;
import com.techexample.example01.model.AppModel;
import com.techexample.example01.service.CurrenciesService;
import com.techexample.example01.util.ApplicationEventBus;
import com.techexample.example01.util.ThrowableEvent;

/**
 * Application controller
 */
public class AppController {
    @Inject
    private AppModel model;
    @Inject
    private CurrenciesService currenciesService;
    @Inject
    private ApplicationEventBus eventBus;

    public void loadCurrencies() {
        model.setState(RUNNING);
        currenciesService.currencies()
            .done(model.getCurrencies()::addAll)
            .fail(throwable -> eventBus.publishAsync(new ThrowableEvent(throwable)))
            .always((state, resolved, rejected) -> model.setState(READY));
    }
}
