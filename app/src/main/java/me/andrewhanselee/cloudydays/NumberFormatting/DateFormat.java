package me.andrewhanselee.cloudydays.NumberFormatting;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Andrew on 2017-01-24.
 */

public class DateFormat {
    public static long second_in_milli = 1000;
    public static long minute_in_milli = second_in_milli * 60;
    public static long hour_in_milli = minute_in_milli * 60;
    public static long day_in_milli = hour_in_milli * 24;


    //Time in UTC (Universal time coordinates)
    public static long getDate(long date) {
        TimeZone tz = TimeZone.getDefault();
        long gmtOffset = tz.getOffset(date);
        return (date + gmtOffset) / day_in_milli;
    }

    public static long normalizeDate(long date) {
        // Normalize to the start of the (UTC) day (local time)
        long newVal = date / day_in_milli * day_in_milli;
        return newVal;
    }

    public static long getLocalFromUTC(long utcDate) {
        TimeZone tz = TimeZone.getDefault();
        long gmtOffset = tz.getOffset(utcDate);
        return utcDate - gmtOffset;
    }

    //adapt to local time
    public static long getUTCFromLocal(long localDate) {
        TimeZone tz = TimeZone.getDefault();
        long gmtOffset = tz.getOffset(localDate);
        return localDate + gmtOffset;
    }

    public static String readableString(Context context, long millis) {
        int flags = DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_NO_YEAR
                | DateUtils.FORMAT_SHOW_WEEKDAY;

        return DateUtils.formatDateTime(context, millis, flags);
    }

    public static String getName(Context context, long millis){
        long dayNum = getDate(millis);
        long currentNum = getDate(System.currentTimeMillis());
        if(dayNum == currentNum){
            return "Today ";
        } else if(dayNum == currentNum+1){
            return "Tomorrow ";
        } else{
            SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE");
            return simpleDate.format(millis);
        }
    }

    public static String easyTime(Context context,long dateInMilli, boolean showFull){
        long localDate = getLocalFromUTC(dateInMilli);
        long dateNum = getDate(localDate);
        long currentDay = getDate(System.currentTimeMillis());

        if(currentDay == dateNum || showFull) {
            //if the viewed date is today, format it as Today is June 24th
            String today = getName(context, localDate);
            String stringToShow = readableString(context, localDate);

            if (dateNum - currentDay < 2) {
                String dayName = new SimpleDateFormat("EEEE").format(localDate);
                return stringToShow.replace(dayName, today);
            } else {
                return stringToShow;
            }
        }
             else if(dateNum < currentDay + 7){
                return getName(context, localDate);
            } else {
                int flags = DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_NO_YEAR
                        | DateUtils.FORMAT_ABBREV_ALL
                        | DateUtils.FORMAT_SHOW_WEEKDAY;

                return DateUtils.formatDateTime(context, localDate, flags);
            }
        }


    }

