package com.share.call_prediction;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class call_log_list extends AppCompatActivity {
    private TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log_list);
        textView = findViewById(R.id.textView);
        buttonCallLog();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void buttonCallLog(){
        textView.setText("Call Logging Started ... ");
        String stringOutput = "";

        Uri uriCallLogs = Uri.parse("content://call_log/calls");
        Cursor cursorCallLogs = getContentResolver().query(uriCallLogs, null,null,null);
        cursorCallLogs.moveToFirst();
        do {
            String stringNumber = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.NUMBER));
            String stringName = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String stringDuration = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DURATION));
            String stringType = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.TYPE));
            String stringDate = cursorCallLogs.getString(cursorCallLogs.getColumnIndex(CallLog.Calls.DATE));


            stringOutput = stringOutput + "Number: " + stringNumber
                    + "\nName: " + stringName
                    + "\nDuration: " + stringDuration
                    + "\n Type: " + stringType
                    + "\n Date: " + stringDate
                    + "\n\n";
        }while (cursorCallLogs.moveToNext());
        textView.setText(stringOutput);
    }
}
