package com.example.regappqrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class QRCodeActivity extends AppCompatActivity {

    ImageView iv_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        iv_qr = findViewById(R.id.iv_qr);
        byte[] byteArray = getIntent().getByteArrayExtra("qr_bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv_qr.setImageBitmap(bitmap);

    }
}