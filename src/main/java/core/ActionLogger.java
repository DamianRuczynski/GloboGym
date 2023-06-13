package core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionLogger {
    public static void setLog(String message) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date());
            PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/data/log.txt", true));
            printWriter.append("******* " + date +" ******* " + message.toUpperCase() + "\n");
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
