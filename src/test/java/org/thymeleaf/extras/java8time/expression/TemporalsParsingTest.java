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


import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import org.junit.Test;



/**
 *
 * @author kai
 */
public class TemporalsParsingTest {
    
    private final Temporals temporals = new Temporals(Locale.ENGLISH, ZoneId.of("Europe/Berlin"));

    @Test
    public void testParse() {
        assertEquals("2015-01-01", temporals.parse("2015-01-01").toString());
        assertEquals("2015-01-01", temporals.parse(" 2015-1-1 ").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse(" 2015-1-1t23:59:59.9 ").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T23:59:59.900Z").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T22:59:59.900+00:00").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse("2015-01-01T21:59:59.900-01:00").toString());
        assertEquals("2015-01-01T23:59:59.900+01:00[Europe/Berlin]", temporals.parse(" 2015-1-1t22:59:59.9z ").toString());
    }

    @Test
    public void testParseWithZone() {
        ZoneId zone = ZoneOffset.UTC;
        assertEquals("2015-01-01", temporals.parse("2015-01-01", zone).toString());
        assertEquals("2015-01-01", temporals.parse(" 2015-1-1 ", zone).toString());
        assertEquals("2015-01-01T23:59:59.900Z", temporals.parse("2015-01-01T23:59:59.900", zone).toString());
        assertEquals("2015-01-01T23:59:59.900Z", temporals.parse(" 2015-1-1t23:59:59.9 ", zone).toString());
        assertEquals("2015-01-01T23:59:59.900Z", temporals.parse("2015-01-01T22:59:59.900+00:00", zone).toString());
        assertEquals("2015-01-01T23:59:59.900Z", temporals.parse(" 2015-1-1t22:59:59.9z ", zone).toString());
    }
}
