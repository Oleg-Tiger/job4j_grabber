package ru.job4j.design.srp;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarInJson implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
    private static final DateFormat DATE_FORMAT
            =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X");

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(DATE_FORMAT.format(calendar.getTime()));
    }

    @Override
    public Calendar deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        Calendar rsl = Calendar.getInstance();
        try {
            rsl.setTime(DATE_FORMAT.parse(element.getAsString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rsl;
    }


}
