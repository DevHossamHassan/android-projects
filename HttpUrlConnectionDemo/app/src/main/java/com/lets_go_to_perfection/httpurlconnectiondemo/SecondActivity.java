package com.lets_go_to_perfection.httpurlconnectiondemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hossam on 12/17/2016.
 */

public class SecondActivity extends AppCompatActivity {
    private ImageView imgView1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imgView1 = (ImageView) findViewById(R.id.imgView1);
        progressBar=(ProgressBar)findViewById(R.id.progress1);

        String url = "https://i.imgur.com/tGbaZCY.jpg";
        // Download image from URL and display within ImageView
        new ImageDownloadTask(imgView1).execute(url);
    }


    // Defines the background task to download and then load the image within the ImageView
    private class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public ImageDownloadTask(ImageView imageView) {

            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        protected Bitmap doInBackground(String... addresses) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                // 1. Declare a URL Connection
                URL url = new URL(addresses[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 2. Open InputStream to connection
                conn.connect();
                in = conn.getInputStream();
                // 3. Download and decode the bitmap using BitmapFactory
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null)
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return bitmap;
        }

        // Fires after the task is completed, displaying the bitmap into the ImageView
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set bitmap image for the result
            if (result != null)
                imageView.setImageBitmap(result);

            progressBar.setVisibility(View.GONE);
        }
    }
}
