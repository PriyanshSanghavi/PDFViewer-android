package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class PdfViewLocal extends AppCompatActivity {
    PDFView pdfShow;
    private static final int PICK_PDF_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view_local);
        pdfShow = findViewById(R.id.pdfshowlocal);
        openFilePicker();
    }
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");

        // Only show activities that can handle PDF files
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri pdfUri = data.getData();
            pdfShow.fromUri(pdfUri).defaultPage(0)
                    .spacing(10)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .load();
        }
    }
}