package com.eztime.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class EZDate {
    private final ZonedDateTime dateTime;

    private EZDate(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static EZDate now(String zoneId) {
        return new EZDate(ZonedDateTime.now(ZoneId.of(zoneId)));
    }

    public EZDate plusDays(long days) {
        return new EZDate(dateTime.plusDays(days));
    }

    public EZDate in(String zoneId) {
        return new EZDate(dateTime.withZoneSameInstant(ZoneId.of(zoneId)));
    }

    public String formatISO() {
        return dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public EZDate startOfDay() {
        ZonedDateTime start = dateTime.toLocalDate().atStartOfDay(dateTime.getZone());
        return new EZDate(start);
    }

    public EZDate endOfDay() {
        ZonedDateTime end = dateTime.withHour(23).withMinute(59).withSecond(59).withNano(999_999_999);
        return new EZDate(end);
    }

    public EZDate startOfMonth() {
        ZonedDateTime start = dateTime.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atStartOfDay(dateTime.getZone());
        return new EZDate(start);
    }

    public EZDate endOfMonth() {
        ZonedDateTime end = dateTime.with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23).withMinute(59).withSecond(59).withNano(999_999_999);
        return new EZDate(end);
    }

    public static List<EZDate> range(EZDate start, EZDate end, int daysStep) {
        List<EZDate> result = new ArrayList<>();
        ZonedDateTime temp = start.dateTime;
        while (!temp.isAfter(end.dateTime)) {
            result.add(new EZDate(temp));
            temp = temp.plusDays(daysStep);
        }
        return result;
    }

    public EZDate add(String relative) {
        ZonedDateTime temp = dateTime;

        String[] tokens = relative.split(" ");
        for (String token : tokens) {
            if (token.endsWith("d")) {
                temp = temp.plusDays(Long.parseLong(token.replace("d", "")));
            } else if (token.endsWith("h")) {
                temp = temp.plusHours(Long.parseLong(token.replace("h", "")));
            } else if (token.endsWith("m")) {
                temp = temp.plusMinutes(Long.parseLong(token.replace("m", "")));
            }
        }

        return new EZDate(temp);
    }

    public String format(String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public String formatShort() {
        return format("dd MMM yyyy");
    }

    public String formatLong() {
        return format("EEEE, MMM dd yyyy HH:mm:ss z");
    }

    public boolean isBefore(EZDate other) {
        return dateTime.isBefore(other.dateTime);
    }

    public boolean isAfter(EZDate other) {
        return dateTime.isAfter(other.dateTime);
    }

    public boolean isSameDay(EZDate other) {
        return dateTime.toLocalDate().equals(other.dateTime.toLocalDate());
    }

    // --- Safe DB and API conversions ---
    public String toDbString() {
        // Always store in UTC to avoid timezone issues
        return dateTime.withZoneSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_INSTANT);
    }

    public static EZDate fromDbString(String dbDate) {
        // Parse ISO UTC string safely
        ZonedDateTime zdt = ZonedDateTime.parse(dbDate, DateTimeFormatter.ISO_DATE_TIME);
        return new EZDate(zdt);
    }

    public String toApiString() {
        // API responses: always ISO-8601 in UTC
        return dateTime.withZoneSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String formatForUser(Locale locale) {
        DateTimeFormatter fmt = DateTimeFormatter
                .ofPattern("dd MMM yyyy, HH:mm z", locale)
                .withZone(dateTime.getZone());
        return dateTime.format(fmt);
    }

    public long toEpochMillis() {
        return dateTime.toInstant().toEpochMilli();
    }

    public static int compare(EZDate d1, EZDate d2) {
        return d1.dateTime.compareTo(d2.dateTime);
    }

    @Override
    public String toString() {
        return formatISO();
    }
}
