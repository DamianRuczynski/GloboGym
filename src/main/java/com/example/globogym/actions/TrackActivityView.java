package com.example.globogym.actions;

import core.ActionLogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackActivityView implements Initializable {
    @FXML
    private ListView<String> logListView;

    private ObservableList<String> logItems;

    public void stopTracking() {
        ActionLogger.stopTracking();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logItems = FXCollections.observableArrayList();
        logListView.setItems(logItems);
        ActionLogger.startTracking(logItems);
    }

}
