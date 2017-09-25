package com.techexample.example01.providers;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Provider;

import org.jdeferred.DeferredManager;
import org.jdeferred.impl.DefaultDeferredManager;

/**
 * Deferred manager Guice provider
 */
public class DeferredManagerProvider implements Provider<DeferredManager> {
    @Inject
    private ExecutorService executorService;

    @Override
    public DeferredManager get() {
        return new DefaultDeferredManager(executorService);
    }
}
