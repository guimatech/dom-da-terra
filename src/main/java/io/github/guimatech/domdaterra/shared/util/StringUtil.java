package io.github.guimatech.domdaterra.shared.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        return str.trim().length() == 0;
    }

    public static String encrypt(String rawString) {
        if (isEmpty(rawString)) {
            return null;
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder.encode(rawString);
    }

    public static String addBrazilCode(String phone) {
        return "+55" + phone;
    }
}
