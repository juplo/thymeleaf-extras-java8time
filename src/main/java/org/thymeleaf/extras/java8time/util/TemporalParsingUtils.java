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
package org.thymeleaf.extras.java8time.util;


import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalQueries;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;
import static java.time.temporal.ChronoField.YEAR;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import org.thymeleaf.util.Validate;



/**
 * Formatting utilities for Java 8 Time objects.
 *
 * @author Kai Moritz
 *
 * @since 2.1.1
 */
public final class TemporalParsingUtils {
  
    private final Locale locale;
    private final ZoneId defaultZoneId;

    private final DateTimeFormatter formatter;


    public TemporalParsingUtils(final Locale locale, final ZoneId defaultZoneId, Clock clock) {
        Validate.notNull(locale, "Locale cannot be null");
        Validate.notNull(defaultZoneId, "ZoneId cannot be null");
        this.locale = locale;
        this.defaultZoneId = defaultZoneId;

        // Create a default formatter for guessing
        formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .parseLenient()
            .optionalStart()
            .appendValueReduced(YEAR, 2, 4, LocalDate.of(2015,1,1))
            .optionalStart()
            .appendLiteral('-')
            .optionalEnd()
            .appendValue(MONTH_OF_YEAR, 2)
            .optionalStart()
            .appendLiteral('-')
            .optionalEnd()
            .appendValue(DAY_OF_MONTH, 2)
            .optionalEnd()
            .optionalStart()
            .appendLiteral(' ')
            .optionalEnd()
            .optionalStart()
            .optionalStart()
            .appendLiteral('T')
            .optionalStart()
            .appendLiteral(' ')
            .optionalEnd()
            .optionalEnd()
            .appendValue(HOUR_OF_DAY, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(NANO_OF_SECOND, 0, 9, true)
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .optionalStart()
            .appendLiteral(' ')
            .optionalEnd()
            .optionalStart()
            .appendOffset("+HH:MM:ss","Z")
            .optionalEnd()
            .optionalStart()
            .optionalStart()
            .appendPattern("X")
            .optionalEnd()
            .optionalStart()
            .appendPattern("XXXX")
            .optionalEnd()
            .optionalStart()
            .optionalStart()
            .appendLiteral(' ')
            .optionalEnd()
            .optionalStart()
            .appendLiteral('[')
            .optionalEnd()
            .appendZoneRegionId()
            .optionalStart()
            .appendLiteral(']')
            .optionalEnd()
            .toFormatter();
    }


    public Temporal parse(String text) {
        return parse(text, defaultZoneId);
    }

    public Temporal parse(String text, ZoneId zone) {
        Validate.notNull(zone, "Zone cannot be null");
        if (text == null)
          return null;
        text = text.trim();
        if (text.isEmpty())
          return null;

        try
        {
          TemporalAccessor temporal = formatter.parse(text);

          ZoneId parsedZone = temporal.query(TemporalQueries.zone());

          boolean hasDate = temporal.isSupported(YEAR);
          boolean hasTime = temporal.isSupported(HOUR_OF_DAY);
          boolean hasZone = parsedZone != null;

          if (hasDate) {
            if (hasTime)
            {
              if (hasZone)
                return ZonedDateTime.from(temporal).withZoneSameInstant(zone);
              else
                return ZonedDateTime.of(LocalDateTime.from(temporal), zone);
            }
            else
              return LocalDate.from(temporal);
          }
          else
          {
            if (hasTime)
              return LocalTime.from(temporal);
            else
              throw new IllegalArgumentException("Could not obtain enough information from parsed temporal: " + temporal);
          }
        }
        catch (Exception e)
        {
          throw new IllegalArgumentException(e);
        }
    }
}
