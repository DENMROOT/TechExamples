package com.techexample.example01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jdeferred.DeferredManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.techexample.example01.controller.AppController;
import com.techexample.example01.model.AppModel;
import com.techexample.example01.restclient.CoinmarketCapRestApi;
import com.techexample.example01.providers.CoinmarketCapRestApiProvider;
import com.techexample.example01.service.CurrenciesService;
import com.techexample.example01.service.impl.CurrenciesServiceImpl;
import com.techexample.example01.providers.DeferredManagerProvider;
import com.techexample.example01.providers.ObjectMapperProvider;
import com.techexample.example01.util.ApplicationEventBus;
import com.techexample.example01.util.ApplicationEventHandler;
import com.techexample.example01.view.AppView;

/**
 * Google guice configuration module
 */
public class AppModule extends AbstractModule {

    @Override
    protected final void configure() {
        bindExecutorService();
        bindDeferredManager();
        bindCurrenciesService();
        bindObjectMapper();
        bindRestAPI();
        bindApplicationEventBus();
        bindApplicationEventHandler();
        bindAppController();
        bindAppModel();
        bindAppView();
    }

    /**
     * Executor service binding
     */
    protected void bindExecutorService() {
        bind(ExecutorService.class)
            .toInstance(Executors.newFixedThreadPool(1));
    }

    /**
     * Deferred manager service binding
     */
    protected void bindDeferredManager() {
        bind(DeferredManager.class)
            .toProvider(DeferredManagerProvider.class)
            .in(Singleton.class);
    }

    /**
     * Currencies service binding
     */
    protected void bindCurrenciesService() {
        bind(CurrenciesService.class)
            .to(CurrenciesServiceImpl.class)
            .in(Singleton.class);
    }

    /**
     * Object mapper binding
     */
    protected void bindObjectMapper() {
        bind(ObjectMapper.class)
            .toProvider(ObjectMapperProvider.class)
            .in(Singleton.class);
    }

    /**
     * Rest apo service binding through provider
     */
    protected void bindRestAPI() {
        bind(CoinmarketCapRestApi.class)
            .toProvider(CoinmarketCapRestApiProvider.class)
            .in(Singleton.class);
    }

    /**
     * Event bus binding
     */
    protected void bindApplicationEventBus() {
        bind(ApplicationEventBus.class)
            .in(Singleton.class);
    }

    /**
     * Event handler binding
     */
    protected void bindApplicationEventHandler() {
        bind(ApplicationEventHandler.class)
            .asEagerSingleton();
    }

    /**
     * Application controller binding
     */
    protected void bindAppController() {
        bind(AppController.class).in(Singleton.class);
    }

    /**
     * App model binding
     */
    protected void bindAppModel() {
        bind(AppModel.class).in(Singleton.class);
    }

    /**
     * View binding
     */
    protected void bindAppView() {
        bind(AppView.class).in(Singleton.class);
    }
}
