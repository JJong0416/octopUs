package com.octopus.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionUtils {

    public static final Integer CODE_SIZE = 8;

    public static String createMissionCode(Integer codeSize){
        return RandomStringUtils.randomAlphanumeric(codeSize);
    }
}
