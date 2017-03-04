package app.kazucon.googlecalendarsample;

import java.util.Calendar;

/**
 * Created by kazuumi on 17/03/04.
 */

public class EventModel {

    private long    beginTimeMillis;
    private long    endTimeMillis;
    private String  title;
    private String  description;
    private String  calendarId;

    public EventModel(
            long beginTimeMills,
            long endTimeMillis,
            String title,
            String description,
            String calendarId) {

        this.beginTimeMillis    = beginTimeMills;
        this.endTimeMillis      = endTimeMillis;
        this.title              = title;
        this.description        = description;
        this.calendarId         = calendarId;
    }

    public long getBeginTimeMillis() {
        return beginTimeMillis;
    }

    public long getEndTimeMillis() {
        return endTimeMillis;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCalendarId() {
        return calendarId;
    }
}
