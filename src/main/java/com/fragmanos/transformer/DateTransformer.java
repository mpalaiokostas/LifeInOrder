package com.fragmanos.transformer;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTransformer {

    private static final String CALENDAR_DATE_PATTERN = "E MMM dd Y";

    public String toCalendarForm(org.joda.time.LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(CALENDAR_DATE_PATTERN);
        return localDate.toString(formatter)+" 00:00:00 GMT+0000 (GMT)";
    }
}
