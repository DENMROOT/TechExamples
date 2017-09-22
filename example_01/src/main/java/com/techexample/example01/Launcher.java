package com.techexample.example01;

import java.util.concurrent.ExecutorService;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.techexample.example01.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Denys_Makarov on 9/22/2017.
 */
public class Launcher extends Application {
    @Inject
    private ExecutorService executorService;
    @Inject
    private AppView view;

    @Override
    public void init() throws Exception {
        Injector injector = Guice.createInjector(new AppModule());
        injector.injectMembers(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(view.createScene());
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        executorService.shutdownNow();
    }

    public static void main(String[] args) {
        launch(Launcher.class, args);
    }
}
