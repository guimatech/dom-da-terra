package io.github.guimatech.domdaterra.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    private static final String BRAZIL_DATE_PATTERN = "dd/MM/yyyy";

    public static String formatDateToBrazilianPattern(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(BRAZIL_DATE_PATTERN));
    }
}
