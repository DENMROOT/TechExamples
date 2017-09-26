package com.techexample.example01.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;

/**
 * Application event handler class
 */
@Listener(references = References.Strong)
public class ApplicationEventHandler {
    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(ApplicationEventHandler.class);

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
        LOG.error("Handling error event {}", event);

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
