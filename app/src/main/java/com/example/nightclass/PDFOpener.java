package com.example.nightclass;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class PDFOpener extends AppCompatActivity {

    PDFView myPDFViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_opener);

        myPDFViewer = findViewById(R.id.pdfViewer);

        String getItem = getIntent().getStringExtra("pdfFileName");

        if(getItem.equals("CANCER BIOLOGY")) {

            myPDFViewer.fromAsset("introduction-to-cancer-biology.pdf").load();
        }

        if(getItem.equals("IMMUNOLOGY_MICROBIOLOGY")) {

            myPDFViewer.fromAsset("Immunology_Microbiology.pdf").load();
        }
    }
}
