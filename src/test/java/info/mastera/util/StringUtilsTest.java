package info.mastera.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void notDoubleNumberTest() {
        Assertions.assertTrue(StringUtils.notDoubleNumber(null));
        Assertions.assertTrue(StringUtils.notDoubleNumber(""));
        Assertions.assertTrue(StringUtils.notDoubleNumber("0.0."));
        Assertions.assertFalse(StringUtils.notDoubleNumber("0.0"));
        Assertions.assertTrue(StringUtils.notDoubleNumber("-1.0"));
        Assertions.assertTrue(StringUtils.notDoubleNumber("e-1.0"));
        Assertions.assertFalse(StringUtils.notDoubleNumber("10"));
        Assertions.assertTrue(StringUtils.notDoubleNumber("-10"));
    }

    @Test
    void notBooleanTest() {
        Assertions.assertTrue(StringUtils.notBoolean(null));
        Assertions.assertTrue(StringUtils.notBoolean(""));
        Assertions.assertTrue(StringUtils.notBoolean("TRUE1"));
        Assertions.assertFalse(StringUtils.notBoolean("TRUE"));
        Assertions.assertFalse(StringUtils.notBoolean("TRuE"));
        Assertions.assertFalse(StringUtils.notBoolean("false"));
    }

    @Test
    void notDateTest() {
        Assertions.assertTrue(StringUtils.notDate(null));
        Assertions.assertTrue(StringUtils.notDate(""));
        Assertions.assertTrue(StringUtils.notDate("2000-13-11"));
        Assertions.assertTrue(StringUtils.notDate("2000-11-31"));
        Assertions.assertTrue(StringUtils.notDate("2000-02-30"));
        Assertions.assertFalse(StringUtils.notDate("2000-11-30"));
    }
}