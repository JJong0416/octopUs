package com.octopus.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUtils {

    public static final Integer PASSWORD_SIZE = 10;

    public static String createRandomUserPassword() {
        return RandomStringUtils.randomNumeric(PASSWORD_SIZE);
    }
}
