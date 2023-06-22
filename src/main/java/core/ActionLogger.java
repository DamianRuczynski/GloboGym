package core;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActionLogger {
    private static boolean isTracked;
    private static ObservableList<String> logItems;

    public static void setLog(String message) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date());
            PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/data/log.txt", true));
            if (isTracked) {
                Platform.runLater(() -> logItems.add(date + " - " + message));
            }
            printWriter.append("******* " + date + " ******* " + message.toUpperCase() + "\n");
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startTracking(ObservableList<String> logItems) {
        isTracked = true;
        ActionLogger.logItems = logItems;
    }

    public static void stopTracking() {
        isTracked = false;
    }

}
