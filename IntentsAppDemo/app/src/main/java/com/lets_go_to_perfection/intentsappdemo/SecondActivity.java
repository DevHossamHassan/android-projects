package com.lets_go_to_perfection.intentsappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private final static String USER_NAME_KEY="username";
    String userName;
    TextView edtUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent=getIntent();
        userName=intent.getStringExtra(USER_NAME_KEY);

        edtUserName=(TextView)findViewById(R.id.txtUserName);
        edtUserName.setText(userName);
    }
}
