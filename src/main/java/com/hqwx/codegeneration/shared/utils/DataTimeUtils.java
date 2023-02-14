package com.hqwx.codegeneration.shared.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/utils/DataTimeUtils.class */
public class DataTimeUtils {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String findCurrentDateTimeStr() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter);
    }

    public static String findCurrentDateStr() {
        LocalDate localDate = LocalDate.now();
        return localDate.format(dateFormatter);
    }

    private DataTimeUtils() {
    }
}
