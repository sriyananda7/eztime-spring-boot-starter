package com.eztime.eztime_spring_boot_starter;

import com.eztime.util.EZDate;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class EZDateTest {

    @Test
    void testFormattingAndZones() {
        EZDate utcNow = EZDate.now("UTC");
        EZDate istNow = utcNow.in("Asia/Kolkata");

        assertNotNull(istNow);
        System.out.println("UTC: " + utcNow.formatISO());
        System.out.println("IST: " + istNow.formatISO());
        System.out.println("For user: " + istNow.formatForUser(Locale.US));
    }

    @Test
    void testRangeAndAdd() {
        EZDate start = EZDate.now("UTC");
        EZDate end = start.plusDays(3);
        var range = EZDate.range(start, end, 1);
        assertEquals(4, range.size());
    }

    @Test
    void testDbStringSerialization() {
        EZDate now = EZDate.now("UTC");
        String dbString = now.toDbString();
        EZDate parsed = EZDate.fromDbString(dbString);

        assertEquals(now.toEpochMillis(), parsed.toEpochMillis());
    }
}
