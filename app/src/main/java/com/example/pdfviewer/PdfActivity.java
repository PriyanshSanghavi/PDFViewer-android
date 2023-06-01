package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfActivity extends AppCompatActivity {
    PDFView pdfShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfShow = findViewById(R.id.pdfshow);
        Intent intent = getIntent();
        String url = intent.getStringExtra("Url");
        new PdfActivity.Retrivepdf().execute(url);
    }
    class Retrivepdf extends AsyncTask<String,Void, InputStream>{
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode() ==200)
                {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Url",Toast.LENGTH_SHORT);
                }
            }catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfShow.fromStream(inputStream).load();
        }
    }
}