package info.mastera.util;

import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern DOUBLE_NUMBER_PATTERN = Pattern.compile("^\\d*\\.?\\d+|\\d+\\.?\\d*$");
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile("^(?i)(true|false)$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^((?:(?:1[9]|2[0-9])\\d{2})(-)(?:(?:(?:0?[13578]|1[02])(-)31)|((0?[1,3-9]|1[0-2])(-)(29|30))))$|^(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(-)0?2(-)29)$|^(?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:0?[1-9])|(?:1[0-2]))(-)(?:0?[1-9]|1\\d|2[0-8])$");

    public static boolean notDoubleNumber(String value) {
        if (value == null) {
            return true;
        }
        return !DOUBLE_NUMBER_PATTERN.matcher(value).matches();
    }

    public static boolean notBoolean(String value) {
        if (value == null) {
            return true;
        }
        return !BOOLEAN_PATTERN.matcher(value).matches();
    }

    public static boolean notDate(String value) {
        if (value == null) {
            return true;
        }
        return !DATE_PATTERN.matcher(value).matches();
    }
}
