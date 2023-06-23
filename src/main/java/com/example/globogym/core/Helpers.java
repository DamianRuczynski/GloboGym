package com.example.globogym.core;
import com.example.globogym.gym_member.Member;
import core.ActionLogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.globogym.MainApplication.trainingsWithMembers;

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

    public static void updateMembersList(int id, ArrayList<Member> list) {
        ActionLogger.setLog("update make");
        trainingsWithMembers.put(id, list);
    }
}
