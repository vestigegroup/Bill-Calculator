package pl.srw.billcalculator.form;

import android.support.annotation.StringRes;

import org.threeten.bp.LocalDate;

import pl.srw.billcalculator.R;
import pl.srw.billcalculator.util.Dates;

/**
 * Created by kseweryn on 30.04.15.
 */
public final class FormValueValidator {

    public interface OnErrorCallback {
        void onError(@StringRes int errorMsgRes);
    }

    public static boolean isValueFilled(final String value, OnErrorCallback callback) {
        if (value.isEmpty()) {
            callback.onError(R.string.reading_missing);
            return false;
        }
        return true;
    }

    public static boolean isValueOrderCorrect(final String prev, final String curr, OnErrorCallback callback) {
        if (!(Integer.parseInt(curr) > Integer.parseInt(prev))) {
            callback.onError(R.string.reading_order_error);
            return false;
        }
        return true;
    }

    public static boolean isDatesOrderCorrect(final String fromDate, final String toDate, OnErrorCallback callback) {
        LocalDate from = Dates.parse(fromDate);
        LocalDate to = Dates.parse(toDate);
        if (!from.isBefore(to)) {
            callback.onError(R.string.date_error);
            return false;
        }
        return true;
    }
}
