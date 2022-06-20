package info.mastera.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

class DateUtilsTest {

    @Test
    void isDateBetweenTest() {
        Assertions.assertFalse(DateUtils.isDateBetween(LocalDateTime.of(2000, 11, 30, 10, 10),
                LocalDate.of(2000, 11, 28), LocalDate.of(2000, 11, 29)));
        Assertions.assertTrue(DateUtils.isDateBetween(LocalDateTime.of(2000, 11, 30, 10, 10),
                LocalDate.of(2000, 11, 28), LocalDate.of(2000, 11, 30)));
        Assertions.assertTrue(DateUtils.isDateBetween(LocalDateTime.of(2000, 11, 30, 10, 10),
                LocalDate.of(2000, 11, 30), LocalDate.of(2000, 11, 30)));
        Assertions.assertTrue(DateUtils.isDateBetween(LocalDateTime.of(2000, 11, 30, 10, 10),
                LocalDate.of(2000, 11, 29), LocalDate.of(2000, 11, 30)));
    }
}