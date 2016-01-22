package com.fragmanos;

import com.fragmanos.transformer.DateTransformer;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.testng.Assert;

/**
 * @author macuser on 1/18/16.
 */
public class DateTransformerTest {

    private static final String ACTUAL_DATE = "Fri Jan 01 2016 00:00:00 GMT+0000 (GMT)";

    DateTransformer dateTransformer = new DateTransformer();

    @Test
    public void testTransactionDateTransformation() throws Exception {
        Assert.assertEquals(ACTUAL_DATE,dateTransformer.toCalendarForm(new LocalDate(2016,01,01)));
    }
}
