package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class MainActivity extends AppCompatActivity {
    Button btnpdf, btnpdflocal;
    EditText pdfurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnpdf = findViewById(R.id.btnpdf);
        pdfurl = (EditText) findViewById(R.id.pdfurl);
        btnpdflocal = findViewById(R.id.btnpdflocal);
        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pdfviewer = new Intent(getApplicationContext(), PdfActivity.class);
                pdfviewer.putExtra("Url", pdfurl.getText().toString());
                startActivity(pdfviewer);
            }
        });
        btnpdflocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent pdfviewer = new Intent(getApplicationContext(), PdfViewLocal.class);
            startActivity(pdfviewer);
            }
        });
    }
}