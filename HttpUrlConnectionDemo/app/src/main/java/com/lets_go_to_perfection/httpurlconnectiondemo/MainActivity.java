package com.lets_go_to_perfection.httpurlconnectiondemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lets_go_to_perfection.httpurlconnectiondemo.utils.Connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView txtView;
    Button btnRequest;
    ProgressBar progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.txtView);
        btnRequest = (Button) findViewById(R.id.btnRequest);
        progressBar=(ProgressBar)findViewById(R.id.progress1);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Connection.isNetworkAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No Internet Connection",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                getData();
            }
        });

    }

    private class NetworkAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... strings) {
            HttpURLConnection conn = null;
            StringBuilder stringBuilder;
            try {
                URL url = new URL(strings[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                InputStream in = conn.getInputStream();

                stringBuilder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                return "null e "+e.getMessage();
            }
        }

        protected void onPostExecute(String result) {
            // This method is executed in the UIThread
            txtView.setText(result);
            progressBar.setVisibility(View.GONE);

        }
    }

    private void getData() {
        // 4. Wrap in AsyncTask and execute in background thread
        new NetworkAsyncTask().execute("https://api.github.com/events");
    }
}
