# 端末内カレンダーへのイベント追加サンプル

### パーミッション宣言
```
<uses-permission android:name="android.permission.READ_CALENDAR"/>
<uses-permission android:name="android.permission.WRITE_CALENDAR"/>
```
6.0以降は、パーミッションリクエスト必要

### 以下の処理で、カレンダーIDを取得する
```
String[] columns = {"_id", "name"};
Uri calendars = CalendarContract.Calendars.CONTENT_URI;
Cursor c = context.getContentResolver().query(calendars, columns, null, null, null);
```

### 以下の処理で、指定したカレンダーIDにイベントを追加する
```
ContentValues cv = new ContentValues();
cv.put(CalendarContract.Events.DTSTART,     model.getBeginTimeMillis());
cv.put(CalendarContract.Events.DTEND,       model.getEndTimeMillis());
cv.put(CalendarContract.Events.TITLE,       model.getTitle());
cv.put(CalendarContract.Events.DESCRIPTION, model.getDescription());
cv.put(CalendarContract.Events.CALENDAR_ID, model.getCalendarId());
cv.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

return context.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, cv);
```
Eventsの項目: https://developer.android.com/reference/android/provider/CalendarContract.Events.html

## 注意
端末内のカレンダー更新のため、すぐにクラウド上に反映されない。
→カレンダーアプリを開けば設定によっては反映。
クラウドに即時反映する必要があるならば、Google Calendar APIを使用する必要あり。
