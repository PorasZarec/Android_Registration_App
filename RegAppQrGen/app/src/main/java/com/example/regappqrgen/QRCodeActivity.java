package com.example.regappqrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class QRCodeActivity extends AppCompatActivity {

    ImageView iv_qr;

    Button bt_goto_Registered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        iv_qr = findViewById(R.id.iv_qr);
        byte[] byteArray = getIntent().getByteArrayExtra("qr_bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        iv_qr.setImageBitmap(bitmap);

        bt_goto_Registered = findViewById(R.id.bt_continueToRegistered);

        bt_goto_Registered.setOnClickListener(view -> {
            Intent intent = new Intent(QRCodeActivity.this, Registered.class);
            startActivity(intent);
        });

    }
}