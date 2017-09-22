package com.techexample.example01.model;

import static com.techexample.example01.model.State.READY;
import static com.techexample.example01.model.State.RUNNING;
import static javafx.collections.FXCollections.observableArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Created by Denys_Makarov on 9/22/2017.
 */
public class AppModel {

    public static final String DEFAULT_CURRENCY_ID = "bitcoin";
    private final ObservableList<Currency> currencies = observableArrayList();

    private StringProperty currencyId;

    private ObjectProperty<State> state;

    public AppModel() {
        stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == RUNNING) {
                currencies.clear();
            }
        });
    }

    public ObservableList<Currency> getCurrencies() {
        return currencies;
    }

    public StringProperty currencyIdProperty() {
        if (currencyId == null) {
            currencyId = new SimpleStringProperty(this, DEFAULT_CURRENCY_ID);
        }
        return currencyId;
    }

    public String getCurrencyId() {
        return currencyIdProperty().get();
    }

    public void setCurrencyId(String currencyId) {
        currencyIdProperty().set(currencyId);
    }

    public State getState() {
        return stateProperty().get();
    }

    public ObjectProperty<State> stateProperty() {
        if (state == null) {
            state = new SimpleObjectProperty<>(this, "state", READY);
        }
        return state;
    }

    public void setState(State state) {
        stateProperty().set(state);
    }
}
