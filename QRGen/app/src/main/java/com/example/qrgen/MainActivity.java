package com.example.qrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText edit_input;
    Button bt_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_input = findViewById(R.id.edit_input);
        bt_generate = findViewById(R.id.bt_generate);

        bt_generate.setOnClickListener(view -> {
            generateQR();
        });
    }

    private void generateQR() {
        String text = edit_input.getText().toString().trim();
        MultiFormatWriter write = new MultiFormatWriter();
        try {
            BitMatrix matrix = write.encode(text, BarcodeFormat.QR_CODE, 1500, 1500);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = new Intent(MainActivity.this, QrActivity.class);
            intent.putExtra("qr_bitmap", byteArray);
            startActivity(intent);

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

}
