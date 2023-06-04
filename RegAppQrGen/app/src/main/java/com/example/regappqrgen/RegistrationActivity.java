package com.example.regappqrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_firstname, et_middlename, et_lastname, et_gradeLevel, et_address, et_mobileNumber, et_nationality, et_email, et_age, et_semester, et_month_input, et_day_input, et_year_input;

    Button bt_generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // GETTING THE INPUTS

        et_firstname = findViewById(R.id.et_firstname);
        et_middlename = findViewById(R.id.et_middlename);
        et_lastname = findViewById(R.id.et_lastname);
        et_gradeLevel = findViewById(R.id.et_gradeLevel);
        et_address = findViewById(R.id.et_address);
        et_mobileNumber = findViewById(R.id.et_mobileNumber);
        et_nationality = findViewById(R.id.et_nationality);
        et_email = findViewById(R.id.et_email);
        et_age = findViewById(R.id.et_age);
        et_semester = findViewById(R.id.et_semester);
        et_month_input = findViewById(R.id.et_month_input);
        et_day_input = findViewById(R.id.et_day_input);
        et_year_input = findViewById(R.id.et_year_input);

        bt_generate = findViewById(R.id.bt_generate);

        bt_generate.setOnClickListener(view -> {
            generateQR();
            Intent intent = new Intent(RegistrationActivity.this, QRCodeActivity.class);
            startActivity(intent);
        });

    }
    private void generateQR() {
        String firstname = et_firstname.getText().toString().trim();
        String middlename = et_middlename.getText().toString().trim();
        String lastname = et_lastname.getText().toString().trim();
        String gradeLevel = et_gradeLevel.getText().toString().trim();
        String address = et_address.getText().toString().trim();
        String mobileNumber = et_mobileNumber.getText().toString().trim();
        String nationality = et_nationality.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String semester = et_semester.getText().toString().trim();
        String monthInput = et_month_input.getText().toString().trim();
        String dayInput = et_day_input.getText().toString().trim();
        String yearInput = et_year_input.getText().toString().trim();

        String text = firstname + "\n" + middlename + "\n" + lastname + "\n" + gradeLevel + "\n" + address + "\n" + mobileNumber + "\n" + nationality + "\n" + email + "\n" + age + "\n" + semester + "\n" + monthInput + "/" + dayInput + "/" + yearInput;

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 1500, 1500);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = new Intent(RegistrationActivity.this, QRCodeActivity.class);
            intent.putExtra("qr_bitmap", byteArray);
            startActivity(intent);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }

//    private void generateQR() {
//        String text = edit_input.getText().toString().trim();
//
//        MultiFormatWriter write = new MultiFormatWriter();
//        try {
//            BitMatrix matrix = write.encode(text, BarcodeFormat.QR_CODE, 1500, 1500);
//            BarcodeEncoder encoder = new BarcodeEncoder();
//            Bitmap bitmap = encoder.createBitmap(matrix);
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//
//            Intent intent = new Intent(RegistrationActivity.this, QRCodeActivity.class);
//            intent.putExtra("qr_bitmap", byteArray);
//            startActivity(intent);
//
//        } catch (WriterException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
