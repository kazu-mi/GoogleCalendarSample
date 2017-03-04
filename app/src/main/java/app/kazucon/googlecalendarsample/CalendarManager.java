package app.kazucon.googlecalendarsample;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by kazuumi on 17/03/04.
 */

public class CalendarManager {

    private Context context;

    public CalendarManager(Context context) {
        this.context = context;
    }

    public List<String> getCalendarIdList() throws SecurityException {
        String[] columns = {"_id", "name"};
        Uri calendars = CalendarContract.Calendars.CONTENT_URI;
        Cursor c = context.getContentResolver().query(calendars, columns, null, null, null);

        ArrayList<String> idList = new ArrayList<>();
        if (c != null && c.moveToFirst()) {
            int idColumnIndex = c.getColumnIndex(columns[0]);
            int nameColumnIndex = c.getColumnIndex(columns[1]);

            do {
                Log.d(context.getPackageName(), c.getString(idColumnIndex) + "," + c.getString(nameColumnIndex));
                idList.add(c.getString(idColumnIndex));
            } while (c.moveToNext());

            c.close();
        }

        return idList;
    }

    public Uri addEvent(EventModel model) throws SecurityException {
        ContentValues cv = new ContentValues();
        cv.put(CalendarContract.Events.DTSTART,     model.getBeginTimeMillis());
        cv.put(CalendarContract.Events.DTEND,       model.getEndTimeMillis());
        cv.put(CalendarContract.Events.TITLE,       model.getTitle());
        cv.put(CalendarContract.Events.DESCRIPTION, model.getDescription());
        cv.put(CalendarContract.Events.CALENDAR_ID, model.getCalendarId());
        cv.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        return context.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, cv);
    }

}
