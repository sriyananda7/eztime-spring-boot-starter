package com.eztime.util;

import java.time.Duration;
import java.time.ZonedDateTime;

public class EZDateDiff {

    public static String humanDiff(ZonedDateTime from, ZonedDateTime to) {
        Duration duration = Duration.between(from, to);
        long seconds = duration.getSeconds();

        if (seconds < 60) return seconds + " seconds";
        long minutes = seconds / 60;
        if (minutes < 60) return minutes + " minutes";
        long hours = minutes / 60;
        if (hours < 24) return hours + " hours";
        long days = hours / 24;
        if (days < 30) return days + " days";
        long months = days / 30;
        if (months < 12) return months + " months";
        long years = months / 12;
        return years + " years";
    }
}
