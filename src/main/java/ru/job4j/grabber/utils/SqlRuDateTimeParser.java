package ru.job4j.grabber.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlRuDateTimeParser implements DateTimeParser {

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
        DateTimeFormatter formatterForDateTime = DateTimeFormatter.ofPattern("dd MM yy, HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(parse, formatterForDateTime);
        return dateTime;
    }

    private static String updateMonth(String parse, String month) {
        String rsl = parse;
        switch (month) {
            case "дек":
                rsl = parse.replace(month, "12");
                break;
            case "янв":
                rsl = parse.replace(month, "01");
                break;
            case "фев":
                rsl = parse.replace(month, "02");
                break;
            case "мар":
                rsl = parse.replace(month, "03");
                break;
            case "апр":
                rsl = parse.replace(month, "04");
                break;
            case "май":
                rsl = parse.replace(month, "05");
                break;
            case "июн":
                rsl = parse.replace(month, "06");
                break;
            case "июл":
                rsl = parse.replace(month, "07");
                break;
            case "авг":
                rsl = parse.replace(month, "08");
                break;
            case "сен":
                rsl = parse.replace(month, "09");
                break;
            case "окт":
                rsl = parse.replace(month, "10");
                break;
            case "ноя":
                rsl = parse.replace(month, "11");
                break;
            default:
                break;
        }
        return rsl;
    }

    private static String updateDay(String parse) {
        StringBuilder sb = new StringBuilder("0");
        sb.append(parse);
        return sb.toString();
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