package io.github.guimatech.domdaterra.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtil {

    public static double moneyBRLToDouble(String value) {
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
        try {
            return nf.parse(value).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
