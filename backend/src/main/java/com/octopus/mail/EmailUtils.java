package com.octopus.mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailUtils {

    public static final Integer CODE_SIZE = 6;

    public static String createEmailAuthentication(){
        return RandomStringUtils.randomAlphanumeric(CODE_SIZE);
    }
}
