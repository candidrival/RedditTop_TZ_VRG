package com.candidrival.reddittop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsible for working with date.
 */

public class DateManager {

    private static DateManager INSTANCE;

    private DateManager(){}

    public static DateManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DateManager();
        }
        return INSTANCE;
    }

    public String printDifference(Date startDate, Date endData) {

        long different = endData.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;

        return elapsedHours +":"+elapsedMinutes;
    }

    public String getDifference(long x) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        String strCurrentTime = simpleDateFormat.format(new Date());

        Date dateUtc = new java.util.Date(x*1000L);
        simpleDateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedUtc = simpleDateFormat.format(dateUtc);

        Date utsDate = simpleDateFormat.parse(formattedUtc);
        Date currentDate = simpleDateFormat.parse(strCurrentTime);

        return printDifference(utsDate, currentDate);

    }

}

