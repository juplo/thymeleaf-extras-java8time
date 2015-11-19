/*
 * Copyright 2015 The THYMELEAF team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thymeleaf.extras.java8time.expression;


import java.time.Clock;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.thymeleaf.extras.java8time.util.TemporalParsingUtils;



/**
 *
 * @author kai
 */
public class TemporalsParsingTest {
    
    private final TemporalParsingUtils temporals =
        new TemporalParsingUtils(
            Locale.ENGLISH,
            ZoneId.of("Europe/Berlin"),
            Clock.systemDefaultZone()
            );

    @Test
    public void testParse() {
        assertEquals("2015-01-01", temporals.parse("2015-01-01").toString());
        assertEquals("2015-01-01", temporals.parse(" 2015-1-1 ").toString());
        assertEquals("2015-01-01", temporals.parse(" 15-1-1 ").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900Z").toString());
//        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900+0").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900+00").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900+0000").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900+00:00").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900+00:00:00").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900GMT").toString());
//        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900GMT+01").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900GMT+01:00").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900 GMT").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900 GMT+01:00").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900[GMT]").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900 [GMT]").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900[Europe/London]").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900[GMT+01:00]").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900 [GMT+01:00]").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900UT+01:00").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T14:59:59.900PST").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T14:59:59.900 PST").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T14:59:59.900[PST]").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T14:59:59.900 [PST]").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:00:00.900+00:00:01").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:00:59.900+0001").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:00:59.900+00:01").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T21:59:59.900-01:00").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T21:59:59.900-0100").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T21:59:59.900-01").toString());

        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse(" 2015-1-1t23:59:59.9 ").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse(" 2015-1-1t22:59:59.9z ").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse(" 15-1-2 1:0:0.9 +02:00:01").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900 [europe/LONDON]").toString());
    }

    @Test
    public void testParseWithZone() {
        ZoneId zone = ZoneOffset.UTC;
        assertEquals("2015-01-01", temporals.parse("2015-01-01", zone).toString());
        assertEquals("2015-01-01", temporals.parse(" 2015-1-1 ", zone).toString());
        assertEquals("2015-01-01T23:59:59.900Z", temporals.parse("2015-01-01T23:59:59.900", zone).toString());
        assertEquals("2015-01-01T23:59:59.900Z", temporals.parse(" 2015-1-1t23:59:59.9 ", zone).toString());
        assertEquals("2015-01-01T22:59:59.900Z", temporals.parse("2015-01-01T22:59:59.900Z", zone).toString());
        assertEquals("2015-01-01T22:59:59.900Z", temporals.parse("2015-01-01T22:59:59.900+00:00", zone).toString());
        assertEquals("2015-01-01T22:59:59.900Z", temporals.parse("2015-01-01T21:59:59.900-01:00", zone).toString());
        assertEquals("2015-01-01T22:59:59.900Z", temporals.parse(" 2015-1-1t22:59:59.9z ", zone).toString());
    }

    @Test
    public void test() {
        DateTimeFormatter formatter;
        TemporalAccessor temporal;
        ZoneOffset offset;
        ZoneId zone;


        formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        temporal = formatter.parse("2015-01-01T23:59:59.900+00:00");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("2015-01-01T23:59:59.900Z", ZonedDateTime.from(temporal).toString());
        assertNotNull(offset);
        assertEquals(ZoneOffset.of("Z"), offset);
        assertEquals(ZoneOffset.of("+0"), offset);
        assertEquals(ZoneOffset.of("+00"), offset);
        assertEquals(ZoneOffset.of("+0000"), offset);
        assertEquals(ZoneOffset.of("+00:00"), offset);
        assertEquals(ZoneOffset.of("+000000"), offset);
        assertEquals(ZoneOffset.of("+00:00:00"), offset);
        assertNull(zone);

        temporal = formatter.parse("2015-01-01T23:59:59.900+01:00");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("2015-01-01T23:59:59.900+01:00", ZonedDateTime.from(temporal).toString());
        assertEquals("2015-01-01T22:59:59.900Z", ZonedDateTime.from(temporal).withZoneSameInstant(ZoneOffset.UTC).toString());
        assertNotNull(offset);
        assertEquals(ZoneOffset.of("+1"), offset);
        assertNull(zone);


        formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC);

        temporal = formatter.parse("2015-01-01T23:59:59.900+01:00");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("2015-01-01T23:59:59.900Z", ZonedDateTime.from(temporal).toString());
        assertEquals("2015-01-01T23:59:59.900Z", ZonedDateTime.from(temporal).withZoneSameInstant(ZoneOffset.UTC).toString());
        assertNotNull(offset);
        assertEquals(ZoneOffset.of("+1"), offset);
        assertNotNull(zone);
        assertEquals(ZoneId.of("Z"), zone);
        assertNotEquals(ZoneId.of("+1"), zone);


        formatter = new DateTimeFormatterBuilder().appendZoneId().toFormatter();

        temporal = formatter.parse("GMT");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("GMT", ZoneId.from(temporal).toString());
        assertNull(offset);
        assertNotNull(zone);
        assertEquals(ZoneId.of("GMT"), zone);
        assertEquals(ZoneId.of("GMT+00:00"), zone);

        temporal = formatter.parse("Europe/Berlin");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("Europe/Berlin", ZoneId.from(temporal).toString());
        assertNull(offset);
        assertNotNull(zone);
        assertEquals(ZoneId.of("Europe/Berlin"), zone);

        temporal = formatter.parse("GMT+01:00");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("GMT+01:00", ZoneId.from(temporal).toString());
        assertNull(offset);
        assertNotNull(zone);
        assertEquals(ZoneId.of("GMT+01:00"), zone);
        assertNotEquals(ZoneId.of("GMT"), zone);


        formatter = new DateTimeFormatterBuilder().appendLocalizedOffset(TextStyle.SHORT).toFormatter();

        temporal = formatter.parse("GMT+01");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("+01:00", ZoneOffset.from(temporal).toString());
        assertNotNull(offset);
        assertEquals(ZoneOffset.of("+01:00"), offset);
        assertNull(zone);

        temporal = formatter.parse("GMT");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("Z", ZoneOffset.from(temporal).toString());
        assertNotNull(offset);
        assertEquals(ZoneOffset.of("+00:00"), offset);
        assertEquals(ZoneOffset.of("Z"), offset);
        assertNull(zone);

        temporal = formatter.parse("GMT+1");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("+01:00", ZoneOffset.from(temporal).toString());
        assertNotNull(offset);
        assertEquals(ZoneOffset.of("+01:00"), offset);
        assertNull(zone);


        formatter = new DateTimeFormatterBuilder().appendZoneText(TextStyle.SHORT).toFormatter();

        temporal = formatter.parse("PST");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("America/Los_Angeles", ZoneId.from(temporal).toString());
        assertNull(offset);
        assertNotNull(zone);
        assertEquals(ZoneId.of("America/Los_Angeles"), zone);

        temporal = formatter.parse("GMT");
        offset = temporal.query(TemporalQueries.offset());
        zone = temporal.query(TemporalQueries.zoneId());
        assertEquals("GMT", ZoneId.from(temporal).toString());
        assertNull(offset);
        assertNotNull(zone);
        assertEquals(ZoneId.of("GMT"), zone);

        try
        {
          formatter.parse("GMT+1");
          fail("GMT+1 should not be parsable as short styled zone-text");
        }
        catch(DateTimeParseException e) {}
    }
}
