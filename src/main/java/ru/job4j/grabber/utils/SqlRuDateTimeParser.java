package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );
    private static final DateTimeFormatter FORMATTER_FOR_DATETIME = DateTimeFormatter.ofPattern("dd MM yy, HH:mm");

    @Override
    public LocalDateTime parse(String parse) {
        String[] dateAndTime = parse.split(" ");
        String day = dateAndTime[0];
        if (!day.equals("сегодня,") && !day.equals("вчера,")) {
            if (day.length() == 1) {
                parse = updateDay(parse);
            }
            parse = updateMonth(parse, dateAndTime[1]);
        } else {
            parse = dateIsTodayOrYesterday(parse, day);
        }
        LocalDateTime dateTime = LocalDateTime.parse(parse, FORMATTER_FOR_DATETIME);
        return dateTime;
    }

    private static String updateMonth(String parse, String month) {
        return parse.replace(month, MONTHS.get(month));
    }

    private static String updateDay(String parse) {
        return "0".concat(parse);
    }

    private static String dateIsTodayOrYesterday(String parse, String day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yy");
        String rslDate;
        if (day.equals("сегодня,")) {
            rslDate = LocalDate.now().format(formatter);
        } else {
            rslDate = LocalDate.now().minusDays(1).format(formatter);
        }
        return parse.replace(day.substring(0, day.length() - 1), rslDate);
    }
}