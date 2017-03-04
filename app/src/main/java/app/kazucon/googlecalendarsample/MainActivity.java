package app.kazucon.googlecalendarsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.Permission;
import java.security.Permissions;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSION_REQUEST_CODE = 1000;
    private boolean isAccessCalendarAllowed = false;
    private CalendarManager calendarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarManager = new CalendarManager(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission_group.CALENDAR
            }, PERMISSION_REQUEST_CODE);
        } else {
            testEvent(calendarManager.getCalendarIdList().get(0));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isAccessCalendarAllowed = true;
                testEvent(calendarManager.getCalendarIdList().get(0));
            } else {
                finish();
            }
        }
    }

    private void testEvent(String calendarId) {
        EventModel model
                = new EventModel(
                System.currentTimeMillis() + 24 * 60 * 60 * 1000,
                System.currentTimeMillis() + 48 * 60 * 60 * 1000,
                "テストタイトル",
                "これはテスト用のイベントです。",
                calendarId);

        Intent intent = new Intent(Intent.ACTION_VIEW, calendarManager.addEvent(model));
        startActivity(intent);
    }
}
