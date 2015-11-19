/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.extras.java8time.expression;

import java.time.Clock;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.thymeleaf.extras.java8time.util.TemporalArrayUtils;
import org.thymeleaf.extras.java8time.util.TemporalCreationUtils;
import org.thymeleaf.extras.java8time.util.TemporalFormattingUtils;
import org.thymeleaf.extras.java8time.util.TemporalListUtils;
import org.thymeleaf.extras.java8time.util.TemporalParsingUtils;
import org.thymeleaf.extras.java8time.util.TemporalSetUtils;
import org.thymeleaf.util.Validate;

/**
 * <p>
 * Utility class to performJava 8 dates ({@link Temporal}) operations.
 * </p>
 * <p>
 * An object of this class is usually available in variable evaluation expressions with the name
 * <tt>#temporals</tt>.
 * </p>
 *
 * @author Jos&eacute; Miguel Samper
 *
 * @since 2.1.0
 */
public final class Temporals {

    private final TemporalCreationUtils temporalCreationUtils;
    private final TemporalParsingUtils temporalParsingUtils;
    private final TemporalFormattingUtils temporalFormattingUtils;
    private final TemporalArrayUtils temporalArrayUtils;
    private final TemporalListUtils temporalListUtils;
    private final TemporalSetUtils temporalSetUtils;

    public Temporals(final Locale locale) {
        this(locale, ZoneId.systemDefault());
    }

    public Temporals(final Locale locale, final ZoneId defaultZoneId) {
        super();
        Validate.notNull(locale, "Locale cannot be null");
        this.temporalCreationUtils = new TemporalCreationUtils();
        this.temporalParsingUtils = new TemporalParsingUtils(locale, defaultZoneId, Clock.system(defaultZoneId));
        this.temporalFormattingUtils = new TemporalFormattingUtils(locale, defaultZoneId);
        this.temporalArrayUtils = new TemporalArrayUtils(locale, defaultZoneId);
        this.temporalListUtils = new TemporalListUtils(locale, defaultZoneId);
        this.temporalSetUtils = new TemporalSetUtils(locale, defaultZoneId);
    }

    /**
     *
     * @return a instance of java.time.LocalDate
     * @since 2.1.0
     */
    public Temporal create(final Object year, final Object month, final Object day) {
        return temporalCreationUtils.create(year, month, day);
    }

    /**
     *
     * @return a instance of java.time.LocalDateTime
     * @since 2.1.0
     */
    public Temporal create(final Object year, final Object month, final Object day,
        final Object hour, final Object minute) {
        return temporalCreationUtils.create(year, month, day, hour, minute);
    }

    /**
     *
     * @return a instance of java.time.LocalDateTime
     * @since 2.1.0
     */
    public Temporal create(final Object year, final Object month, final Object day,
        final Object hour, final Object minute, final Object second) {
        return temporalCreationUtils.create(year, month, day, hour, minute, second);
    }

    /**
     *
     * @return a instance of java.time.LocalDateTime
     * @since 2.1.0
     */
    public Temporal create(final Object year, final Object month, final Object day,
        final Object hour, final Object minute, final Object second, final Object nanosecond) {
        return temporalCreationUtils.create(year, month, day, hour, minute, second, nanosecond);
    }
    
    /**
     *
     * @return a instance of java.time.LocalDate
     * @since 2.1.0
     */
    public Temporal createDate(final String isoDate) {
        return temporalCreationUtils.createDate(isoDate);
    }
    
    /**
     *
     * @return a instance of java.time.LocalDateTime
     * @since 2.1.0
     */
    public Temporal createDateTime(final String isoDate) {
        return temporalCreationUtils.createDateTime(isoDate);
    }
    
    /**
     *
     * @return a instance of java.time.LocalDate
     * @since 2.1.0
     */
    public Temporal createDate(final String isoDate, final String pattern) {
        return temporalCreationUtils.createDate(isoDate, pattern);
    }
    
    /**
     *
     * @return a instance of java.time.LocalDateTime
     * @since 2.1.0
     */
    public Temporal createDateTime(final String isoDate, final String pattern) {
        return temporalCreationUtils.createDateTime(isoDate, pattern);
    }

    /**
     *
     * @return a instance of java.time.LocalDateTime
     * @since 2.1.0
     */
    public Temporal createNow() {
        return temporalCreationUtils.createNow();
    }

    /**
     *
     * @return a instance of java.time.ZonedDateTime
     * @since 2.1.0
     */
    public Temporal createNowForTimeZone(final Object zoneId) {
        return temporalCreationUtils.createNowForTimeZone(zoneId);
    }

    /**
     *
     * @return a instance of java.time.LocalDate
     * @since 2.1.0
     */
    public Temporal createToday() {
        return temporalCreationUtils.createToday();
    }

    /**
     *
     * @return a instance of java.time.ZonedDateTime with 00:00:00.000 for the time part
     * @since 2.1.0
     */
    public Temporal createTodayForTimeZone(final Object zoneId) {
        return temporalCreationUtils.createTodayForTimeZone(zoneId);
    }

    /**
     * @param text The text to parse
     * @return An {@link OffsetTime}, if only time-data is available, a {@link LocalDate} if only date-data is available and a {@link ZonedDateTime} if both is available.
     * @since 2.1.1
     */
    public Temporal parse(String text) {
        return temporalParsingUtils.parse(text);
    }

    /**
     * @param text The text to parse
     * @param zone The {@link ZoneId} to use for adjusting during parsing
     * @return An {@link LocalTime}, if only time-data is available, a {@link LocalDate} if only date-data is available and a {@link ZonedDateTime} if both is available.
     * @since 2.1.1
     */
    public Temporal parse(String text, ZoneId zone) {
        return temporalParsingUtils.parse(text, zone);
    }

    /**
     *
     * @since 2.1.0
     */
    public String format(final Temporal target) {
        return temporalFormattingUtils.format(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayFormat(final Object[] target) {
        return temporalArrayUtils.arrayFormat(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listFormat(final List<? extends Temporal> target) {
        return temporalListUtils.listFormat(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setFormat(final Set<? extends Temporal> target) {
        return temporalSetUtils.setFormat(target);
    }

    /**
     *
     * @since 2.1.1
     */
    public String format(final Temporal target, final Locale locale) {
        return temporalFormattingUtils.format(target, locale);
    }

    /**
     *
     * @since 2.1.1
     */
    public String[] arrayFormat(final Object[] target, final Locale locale) {
        return temporalArrayUtils.arrayFormat(target, locale);
    }

    /**
     *
     * @since 2.1.1
     */
    public List<String> listFormat(final List<? extends Temporal> target, final Locale locale) {
        return temporalListUtils.listFormat(target, locale);
    }

    /**
     *
     * @since 2.1.1
     */
    public Set<String> setFormat(final Set<? extends Temporal> target, final Locale locale) {
        return temporalSetUtils.setFormat(target, locale);
    }

    /**
     *
     * @since 2.1.0
     */
    public String format(final Temporal target, final String pattern) {
        return temporalFormattingUtils.format(target, pattern);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayFormat(final Object[] target, final String pattern) {
        return temporalArrayUtils.arrayFormat(target, pattern);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listFormat(final List<? extends Temporal> target, final String pattern) {
        return temporalListUtils.listFormat(target, pattern);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setFormat(final Set<? extends Temporal> target, final String pattern) {
        return temporalSetUtils.setFormat(target, pattern);
    }

    /**
     *
     * @since 2.1.1
     */
    public String format(final Temporal target, final String pattern, final Locale locale) {
        return temporalFormattingUtils.format(target, pattern, locale);
    }

    /**
     *
     * @since 2.1.1
     */
    public String[] arrayFormat(final Object[] target, final String pattern, final Locale locale) {
        return temporalArrayUtils.arrayFormat(target, pattern, locale);
    }

    /**
     *
     * @since 2.1.1
     */
    public List<String> listFormat(final List<? extends Temporal> target, final String pattern, final Locale locale) {
        return temporalListUtils.listFormat(target, pattern, locale);
    }

    /**
     *
     * @since 2.1.1
     */
    public Set<String> setFormat(final Set<? extends Temporal> target, final String pattern, final Locale locale) {
        return temporalSetUtils.setFormat(target, pattern, locale);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer day(final Temporal target) {
        return temporalFormattingUtils.day(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayDay(final Object[] target) {
        return temporalArrayUtils.arrayDay(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listDay(final List<? extends Temporal> target) {
        return temporalListUtils.listDay(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setDay(final Set<? extends Temporal> target) {
        return temporalSetUtils.setDay(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer month(final Temporal target) {
        return temporalFormattingUtils.month(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayMonth(final Object[] target) {
        return temporalArrayUtils.arrayMonth(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listMonth(final List<? extends Temporal> target) {
        return temporalListUtils.listMonth(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setMonth(final Set<? extends Temporal> target) {
        return temporalSetUtils.setMonth(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String monthName(final Temporal target) {
        return temporalFormattingUtils.monthName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayMonthName(final Object[] target) {
        return temporalArrayUtils.arrayMonthName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listMonthName(final List<? extends Temporal> target) {
        return temporalListUtils.listMonthName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setMonthName(final Set<? extends Temporal> target) {
        return temporalSetUtils.setMonthName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String monthNameShort(final Temporal target) {
        return temporalFormattingUtils.monthNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayMonthNameShort(final Object[] target) {
        return temporalArrayUtils.arrayMonthNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listMonthNameShort(final List<? extends Temporal> target) {
        return temporalListUtils.listMonthNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setMonthNameShort(final Set<? extends Temporal> target) {
        return temporalSetUtils.setMonthNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer year(final Temporal target) {
        return temporalFormattingUtils.year(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayYear(final Object[] target) {
        return temporalArrayUtils.arrayYear(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listYear(final List<? extends Temporal> target) {
        return temporalListUtils.listYear(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setYear(final Set<? extends Temporal> target) {
        return temporalSetUtils.setYear(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer dayOfWeek(final Temporal target) {
        return temporalFormattingUtils.dayOfWeek(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayDayOfWeek(final Object[] target) {
        return temporalArrayUtils.arrayDayOfWeek(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listDayOfWeek(final List<? extends Temporal> target) {
        return temporalListUtils.listDayOfWeek(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setDayOfWeek(final Set<? extends Temporal> target) {
        return temporalSetUtils.setDayOfWeek(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String dayOfWeekName(final Temporal target) {
        return temporalFormattingUtils.dayOfWeekName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayDayOfWeekName(final Object[] target) {
        return temporalArrayUtils.arrayDayOfWeekName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listDayOfWeekName(final List<? extends Temporal> target) {
        return temporalListUtils.listDayOfWeekName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setDayOfWeekName(final Set<? extends Temporal> target) {
        return temporalSetUtils.setDayOfWeekName(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String dayOfWeekNameShort(final Temporal target) {
        return temporalFormattingUtils.dayOfWeekNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayDayOfWeekNameShort(final Object[] target) {
        return temporalArrayUtils.arrayDayOfWeekNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listDayOfWeekNameShort(final List<? extends Temporal> target) {
        return temporalListUtils.listDayOfWeekNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setDayOfWeekNameShort(final Set<? extends Temporal> target) {
        return temporalSetUtils.setDayOfWeekNameShort(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer hour(final Temporal target) {
        return temporalFormattingUtils.hour(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayHour(final Object[] target) {
        return temporalArrayUtils.arrayHour(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listHour(final List<? extends Temporal> target) {
        return temporalListUtils.listHour(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setHour(final Set<? extends Temporal> target) {
        return temporalSetUtils.setHour(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer minute(final Temporal target) {
        return temporalFormattingUtils.minute(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayMinute(final Object[] target) {
        return temporalArrayUtils.arrayMinute(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listMinute(final List<? extends Temporal> target) {
        return temporalListUtils.listMinute(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setMinute(final Set<? extends Temporal> target) {
        return temporalSetUtils.setMinute(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer second(final Temporal target) {
        return temporalFormattingUtils.second(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arraySecond(final Object[] target) {
        return temporalArrayUtils.arraySecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listSecond(final List<? extends Temporal> target) {
        return temporalListUtils.listSecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setSecond(final Set<? extends Temporal> target) {
        return temporalSetUtils.setSecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer nanosecond(final Temporal target) {
        return temporalFormattingUtils.nanosecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Integer[] arrayNanosecond(final Object[] target) {
        return temporalArrayUtils.arrayNanosecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<Integer> listNanosecond(final List<? extends Temporal> target) {
        return temporalListUtils.listNanosecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<Integer> setNanosecond(final Set<? extends Temporal> target) {
        return temporalSetUtils.setNanosecond(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String formatISO(final Temporal target) {
        return temporalFormattingUtils.formatISO(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public String[] arrayFormatISO(final Object[] target) {
        return temporalArrayUtils.arrayFormatISO(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public List<String> listFormatISO(final List<? extends Temporal> target) {
        return temporalListUtils.listFormatISO(target);
    }

    /**
     *
     * @since 2.1.0
     */
    public Set<String> setFormatISO(final Set<? extends Temporal> target) {
        return temporalSetUtils.setFormatISO(target);
    }

}
