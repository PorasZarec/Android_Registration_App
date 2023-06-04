package com.example.qrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class QrActivity extends AppCompatActivity {

    ImageView iv_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        iv_qr = findViewById(R.id.iv_qr);
        byte[] byteArray = getIntent().getByteArrayExtra("qr_bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv_qr.setImageBitmap(bitmap);
    }
}
