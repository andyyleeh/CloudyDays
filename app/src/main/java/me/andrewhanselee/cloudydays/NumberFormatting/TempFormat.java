package me.andrewhanselee.cloudydays.NumberFormatting;

import android.content.Context;

import org.json.JSONException;

import me.andrewhanselee.cloudydays.Network;
import me.andrewhanselee.cloudydays.R;

/**
 * Created by Andrew on 2017-01-25.
 */

public class TempFormat {
    private static final String LOG_TAG = TempFormat.class.getSimpleName();

    public static String formatTemp (Context context, double temperatureJson){
        int temperatureFormatRID = R.string.format_temperature_celsius;
        if(Network.UNITS != "metric"){
            temperatureFormatRID = R.string.format_temperature_fahrenheit;
        }
        return String.format(context.getString(temperatureFormatRID), temperatureJson);
    }

    //displays temp in form HIGH / LOW

    public static String formatHL (Context context, double high, double low){
        long roundH = Math.round(high);
        long roundL = Math.round(low);

        String formattedH = formatTemp(context, roundH);
        String formattedL = formatTemp(context, roundL);

        String show = formattedH + " / " + formattedL;
        return show;
    }

    public static String conditionFormat (Context context, int descriptionID){
        int id;
        if (descriptionID >= 200 && descriptionID <= 232) {
            id = R.string.condition_2xx;
        } else if (descriptionID >= 300 && descriptionID <= 321) {
            id = R.string.condition_3xx;
        } else switch (descriptionID) {
            case 500:
                id = R.string.condition_500;
                break;
            case 501:
                id = R.string.condition_501;
                break;
            case 502:
                id = R.string.condition_502;
                break;
            case 503:
                id = R.string.condition_503;
                break;
            case 504:
                id = R.string.condition_504;
                break;
            case 511:
                id = R.string.condition_511;
                break;
            case 520:
                id = R.string.condition_520;
                break;
            case 531:
                id = R.string.condition_531;
                break;
            case 600:
                id = R.string.condition_600;
                break;
            case 601:
                id = R.string.condition_601;
                break;
            case 602:
                id = R.string.condition_602;
                break;
            case 611:
                id = R.string.condition_611;
                break;
            case 612:
                id = R.string.condition_612;
                break;
            case 615:
                id = R.string.condition_615;
                break;
            case 616:
                id = R.string.condition_616;
                break;
            case 620:
                id = R.string.condition_620;
                break;
            case 621:
                id = R.string.condition_621;
                break;
            case 622:
                id = R.string.condition_622;
                break;
            case 701:
                id = R.string.condition_701;
                break;
            case 711:
                id = R.string.condition_711;
                break;
            case 721:
                id = R.string.condition_721;
                break;
            case 731:
                id = R.string.condition_731;
                break;
            case 741:
                id = R.string.condition_741;
                break;
            case 751:
                id = R.string.condition_751;
                break;
            case 761:
                id = R.string.condition_761;
                break;
            case 762:
                id = R.string.condition_762;
                break;
            case 771:
                id = R.string.condition_771;
                break;
            case 781:
                id = R.string.condition_781;
                break;
            case 800:
                id = R.string.condition_800;
                break;
            case 801:
                id = R.string.condition_801;
                break;
            case 802:
                id = R.string.condition_802;
                break;
            case 803:
                id = R.string.condition_803;
                break;
            case 804:
                id = R.string.condition_804;
                break;
            case 900:
                id = R.string.condition_900;
                break;
            case 901:
                id = R.string.condition_901;
                break;
            case 902:
                id = R.string.condition_902;
                break;
            case 903:
                id = R.string.condition_903;
                break;
            case 904:
                id = R.string.condition_904;
                break;
            case 905:
                id = R.string.condition_905;
                break;
            case 906:
                id = R.string.condition_906;
                break;
            case 951:
                id = R.string.condition_951;
                break;
            case 952:
                id = R.string.condition_952;
                break;
            case 953:
                id = R.string.condition_953;
                break;
            case 954:
                id = R.string.condition_954;
                break;
            case 955:
                id = R.string.condition_955;
                break;
            case 956:
                id = R.string.condition_956;
                break;
            case 957:
                id = R.string.condition_957;
                break;
            case 958:
                id = R.string.condition_958;
                break;
            case 959:
                id = R.string.condition_959;
                break;
            case 960:
                id = R.string.condition_960;
                break;
            case 961:
                id = R.string.condition_961;
                break;
            case 962:
                id = R.string.condition_962;
                break;
            default:
                return context.getString(R.string.condition_unknown, descriptionID);
        }
        return context.getString(id);
    }

    // add wind stuff and icons later on

}

