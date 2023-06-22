package core;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class LogTracker extends Thread {
    private volatile boolean running = true;
    private ObservableList<String> logItems;

    public LogTracker(ObservableList<String> logItems) {
        this.logItems = logItems;
    }

    @Override
    public void run() {
        while (running) {
            String newLog = "New log message: " + System.currentTimeMillis();
            Platform.runLater(() -> logItems.add(newLog));

        }
    }

    public void stopTracking() {
        running = false;
    }
}
