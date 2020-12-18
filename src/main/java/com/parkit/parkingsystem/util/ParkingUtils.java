package com.parkit.parkingsystem.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ParkingUtils {

    public static long CalculateDurationBetweenDate(Date inDate, Date outDate) {
        LocalDateTime inDateTime = LocalDateTime.ofInstant(inDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime outDateTime = LocalDateTime.ofInstant(outDate.toInstant(), ZoneId.systemDefault());

        return (Duration.between(inDateTime, outDateTime).toMinutes());

    }
}
