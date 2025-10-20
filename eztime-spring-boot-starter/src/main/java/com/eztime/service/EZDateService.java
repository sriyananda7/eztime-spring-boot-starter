package com.eztime.service;

import com.eztime.util.EZDate;
import com.eztime.util.EZDateDiff;

import java.time.ZonedDateTime;

public class EZDateService {

    public String getNow(String zone) {
        return EZDate.now(zone).formatISO();
    }

    public String convert(String fromZone, String toZone) {
        return EZDate.now(fromZone).in(toZone).formatISO();
    }

    public String addDays(String zone, int days) {
        return EZDate.now(zone).plusDays(days).formatISO();
    }

    public String humanDiff(String fromIso, String toIso) {
        ZonedDateTime from = ZonedDateTime.parse(fromIso);
        ZonedDateTime to = ZonedDateTime.parse(toIso);
        return EZDateDiff.humanDiff(from, to);
    }
}
