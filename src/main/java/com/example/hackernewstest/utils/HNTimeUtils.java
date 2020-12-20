package com.example.hackernewstest.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class HNTimeUtils {

    public static Long differenceFromCurrentTime(Long time) {
        if (time == null) {
            return Long.MAX_VALUE;
        }
        return Instant.ofEpochMilli(time)
                .until(Instant.now(), ChronoUnit.MINUTES);
    }
}
