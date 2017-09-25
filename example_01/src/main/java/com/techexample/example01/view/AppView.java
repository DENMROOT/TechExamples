package com.techexample.example01.view;

import com.techexample.example01.controller.AppController;
import com.techexample.example01.model.AppModel;
import com.techexample.example01.model.Currency;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import org.reactfx.EventStreams;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;

import static com.techexample.example01.model.State.DISABLED;
import static com.techexample.example01.model.State.READY;
import static java.util.Objects.requireNonNull;

/**
 * JavaFX View part of application
 */
public class AppView {
    @Inject
    private AppController controller;
    @Inject
    private AppModel model;

    @FXML
    private TextField currency;
    @FXML
    private Button loadButton;
    @FXML
    private ProgressBar progress;
    @FXML
    private Label total;
    @FXML
    private ListView<Currency> currencies;

    private BooleanProperty enabled = new SimpleBooleanProperty(this, "enabled", true);
    private BooleanProperty running = new SimpleBooleanProperty(this, "running", false);

    public Scene createScene() {
        String basename = getClass().getPackage().getName().replace('.', '/') + "/app";
        URL fxml = getClass().getClassLoader().getResource(basename + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxml);
        fxmlLoader.setControllerFactory(param -> AppView.this);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        currency.textProperty().addListener((observable, oldValue, newValue) -> {
            model.setState(isBlank(newValue) ? DISABLED : READY);
        });

        model.stateProperty().addListener((observable, oldValue, newValue) ->
            Platform.runLater(() -> {
                switch (newValue) {
                    case DISABLED:
                        enabled.setValue(false);
                        running.setValue(false);
                        break;
                    case READY:
                        enabled.setValue(true);
                        running.setValue(false);
                        break;
                    case RUNNING:
                        enabled.setValue(false);
                        running.setValue(true);
                        break;
                    default:
                        break;
                }
            }));

        ObservableList<Currency> items = createJavaFXThreadProxyList(model.getCurrencies()
            .sorted(Comparator.comparing(Currency::getRank)));

        currencies.setItems(items);
        EventStreams.sizeOf(items).subscribe(v -> total.setText(String.valueOf(v)));
        currency.textProperty().bindBidirectional(model.currencyIdProperty());
//        loadButton.disableProperty().bind(Bindings.not(enabled));
        progress.visibleProperty().bind(running);

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(basename + ".css", "bootstrapfx.css");
        return scene;
    }

    public void load(ActionEvent event) {
        if (currency.textProperty().getValueSafe().length() > 0) {
            controller.loadCurrency(currency.textProperty().get());
        } else {
            controller.loadCurrencies();
        }
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    private static <E> ObservableList<E> createJavaFXThreadProxyList(ObservableList<E> source) {
        requireNonNull(source, "Argument 'source' must not be null");
        return new JavaFXThreadProxyObservableList<>(source);
    }

    /**
     * App view model events container
     * @param <E>
     */
    private static class JavaFXThreadProxyObservableList<E> extends TransformationList<E, E> {
        protected JavaFXThreadProxyObservableList(ObservableList<E> source) {
            super(source);
        }

        @Override
        protected void sourceChanged(ListChangeListener.Change<? extends E> c) {
            if (Platform.isFxApplicationThread()) {
                fireChange(c);
            } else {
                Platform.runLater(() -> fireChange(c));
            }
        }

        @Override
        public int getSourceIndex(int index) {
            return index;
        }

        @Override
        public E get(int index) {
            return getSource().get(index);
        }

        @Override
        public int size() {
            return getSource().size();
        }
    }
}