package com.ieee.girish.sywpush;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.pushText);
    }

    public void push(View view)
    {
        String pushText = editText.getText().toString();

        ParseQuery pushQuery = ParseInstallation.getQuery();

        LinkedList<String> channels = new LinkedList<String>();
        channels.add("");
        channels.add("Test");
        channels.add("Testing");
        //pushQuery.whereEqualTo("channels", "");

        ParsePush push = new ParsePush();
        push.setChannels(channels);
        push.setQuery(pushQuery);
        if(!pushText.equals("")) {
            push.setMessage(pushText);
            ParseObject notification = new ParseObject("Notification");
            notification.put("NotString", pushText);
            notification.saveInBackground();
            push.sendInBackground();
        }
        else
            Toast.makeText(MainActivity.this, "Can't be null.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
