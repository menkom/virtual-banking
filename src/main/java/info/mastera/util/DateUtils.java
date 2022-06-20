package info.mastera.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static LocalDate convert(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date format exception.");
        }
    }

    public static boolean isDateBetween(LocalDateTime checkableDate, LocalDate startDate, LocalDate finishDate) {
        if (startDate.compareTo(finishDate) > 0) {
            throw new IllegalArgumentException("Finish date can't be earlier then start date.");
        }
        return !checkableDate.toLocalDate().isAfter(finishDate) && !startDate.isAfter(checkableDate.toLocalDate());
    }
}
