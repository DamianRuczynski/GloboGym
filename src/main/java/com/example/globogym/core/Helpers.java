package com.example.globogym.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
    public static String parseDataToStringFormat(Date date){
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = outputFormat.format(date);
        return formattedDate;
    }
}
