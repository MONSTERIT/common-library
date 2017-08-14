package net.wepla.library_common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.TypedValue;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bek on 06/01/2017.
 */

public class Function {
    public static String createEventDetailsDateTime(String evestart) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(evestart);


            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Date getDate(String evestart) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();
        try {
            date = simpleDateFormat.parse(evestart);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;
    }

    public static String createEventDetailsDateTime(long evestart) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);

        return simpleDateFormat.format(DateUtils.clearDate(evestart));

    }

    public static String createEventDetailsDate(long evestart) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

        return simpleDateFormat.format(DateUtils.clearDate(evestart));

    }


    public static String getDifferenceDate(String evestart, String eveend) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start = simpleDateFormat.parse(evestart);
            Date end = simpleDateFormat.parse(eveend);


            long difference = Math.abs(end.getTime() - start.getTime());
            long differenceDates = (difference / (24 * 60 * 60 * 1000)) + 1;

            //Convert long to String
            String dayDifference = Long.toString(differenceDates);
            Log.d("dayDifference", dayDifference);
            return dayDifference;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String createTime(String from_time) {
        if (from_time != null) {
            String hour = from_time.substring(0, 2);
            String minute = from_time.substring(2, 4);
            String second = "00";
            if (from_time.length() > 4) {
                second = from_time.substring(4);
            }
            return hour + ":" + minute + ":" + second;
        } else {
            return "";
        }
    }

    public static String createMeetupDateTile(String headerTitle) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(headerTitle);


            simpleDateFormat = new SimpleDateFormat("MM.dd EEE", Locale.KOREA);

            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return headerTitle;
    }

    public static String createMeetupDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(dateString);


            simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd EEE", Locale.KOREA);

            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateString;
    }

    public static int dp2px(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static String convertArrayToStringWithComma(String[] categories) {
        String withComma = "";
        for (int i = 0; i < categories.length; i++) {
            if (i == 0) {
                withComma += categories[i];
            } else {
                withComma += "," + categories[i];
            }
        }

        return withComma.length() > 0 ? withComma : null;
    }

    public static String createPointText(String s) {
        String format = "";
        if (s != null) {

            if (s.startsWith("-")) {
                if (s.length() < 5) {
                    format = s;
                } else if (s.length() < 8) {
                    format = s.substring(0, s.length() - 4) + "," + s.substring(s.length() - 3, s.length());
                } else {
                    format = s;
                }
            } else {
                if (s.length() < 4) {
                    format = s;
                } else if (s.length() < 7) {
                    format = s.substring(0, s.length() - 3) + "," + s.substring(s.length() - 3, s.length());
                } else {
                    format = s;
                }
            }
        }
        return format;
    }

    public static String dataCheck(Object s) {
        if (s == null) {
            return "";
        }
        if ((s instanceof String) && (((String) s).trim().length() == 0)) {
            return "";
        }
        return String.valueOf(s);
    }

    public static String dataCheckSpec(Object s) {
        if (s == null) {
            return "-";
        }
        if ((s instanceof String) && (((String) s).trim().length() == 0)) {
            return "-";
        }
        return String.valueOf(s);
    }

    public static String createPrice(Integer integer) {
        if (integer != null) {
            DecimalFormat format = new DecimalFormat("###,###");
            return format.format(integer);
        } else {
            return "";
        }
    }

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
