package com.example.globogym.core;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Helpers {
    public static String parseDataToStringFormat(Date date){
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = outputFormat.format(date);
        return formattedDate;
    }

    public static <T extends Identifiable> void repairIds(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            item.setId(i + 1);
        }
    }
}