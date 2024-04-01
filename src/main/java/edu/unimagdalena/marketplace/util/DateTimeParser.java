package edu.unimagdalena.marketplace.util;

import edu.unimagdalena.marketplace.constant.ValidationMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static LocalDateTime parse(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ValidationMessage.WrongDateFormat + "." + dateTimeString);
        }
    }
}
