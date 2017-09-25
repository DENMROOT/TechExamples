package com.techexample.example01.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import net.engio.mbassy.listener.Handler;

/**
 * Application event handler class
 */
public class ApplicationEventHandler {
    @Inject
    private ApplicationEventBus eventBus;

    @PostConstruct
    private void init() {
        eventBus.subscribe(this);
    }

    @PreDestroy
    private void destroy() {
        eventBus.unsubscribe(this);
    }

    @Handler
    public void handleThrowable(ThrowableEvent event) {
        Platform.runLater(() -> {
            TitledPane pane = new TitledPane();
            pane.setId("stacktrace");
            pane.setCollapsible(false);
            pane.setText("Stacktrace");
            TextArea textArea = new TextArea();
            textArea.setEditable(false);
            pane.setContent(textArea);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            event.getThrowable().printStackTrace(new PrintStream(baos));
            textArea.setText(baos.toString());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An unexpected error occurred");
            alert.getDialogPane().setContent(pane);
            alert.showAndWait();
        });
    }
}
