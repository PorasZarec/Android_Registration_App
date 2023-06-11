package com.example.regappqrgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

public class RegistrationActivity extends AppCompatActivity {

    EditText et_firstname, et_middlename, et_lastname, et_gradeLevel, et_address, et_mobileNumber, et_nationality, et_email, et_age, et_semester, et_month_input, et_day_input, et_year_input;

    Button bt_generate;
    Spinner genderSpinner, courseSpinner, modeOfPaymentSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        genderSpinner = findViewById(R.id.gender_spinner);
        courseSpinner = findViewById(R.id.course_spinner);
        modeOfPaymentSpinner = findViewById(R.id.modeOfPayment_Spinner);

// Create an ArrayAdapter for the Spinners
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> courseAdapter = ArrayAdapter.createFromResource(this,
                R.array.course_options, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> paymentAdapter = ArrayAdapter.createFromResource(this,
                R.array.modeOfPayment_options, android.R.layout.simple_spinner_item);

// Set the dropdown layout style
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Set the adapters to the Spinners
        genderSpinner.setAdapter(genderAdapter);
        courseSpinner.setAdapter(courseAdapter);
        modeOfPaymentSpinner.setAdapter(paymentAdapter);

// Set the default selections to the first items
        genderSpinner.setSelection(0);
        courseSpinner.setSelection(0);
        modeOfPaymentSpinner.setSelection(0);


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

//    private void generateQR() {
//        String firstname = et_firstname.getText().toString().trim();
//        String middlename = et_middlename.getText().toString().trim();
//        String lastname = et_lastname.getText().toString().trim();
//        String gradeLevel = et_gradeLevel.getText().toString().trim();
//        String address = et_address.getText().toString().trim();
//        String mobileNumber = et_mobileNumber.getText().toString().trim();
//        String nationality = et_nationality.getText().toString().trim();
//        String email = et_email.getText().toString().trim();
//        String age = et_age.getText().toString().trim();
//        String semester = et_semester.getText().toString().trim();
//        String monthInput = et_month_input.getText().toString().trim();
//        String dayInput = et_day_input.getText().toString().trim();
//        String yearInput = et_year_input.getText().toString().trim();
//
//        String selectedGender = genderSpinner.getSelectedItem().toString();
//        String selectedCourse = courseSpinner.getSelectedItem().toString();
//        String selectedModeOfPayment = modeOfPaymentSpinner.getSelectedItem().toString();
//
//        String text = firstname + "\n" + middlename + "\n" + lastname + "\n" + gradeLevel + "\n" + address + "\n" + mobileNumber + "\n" + nationality + "\n" + email + "\n" + age + "\n" + semester + "\n" + monthInput + "/" + dayInput + "/" + yearInput + "\n" + selectedGender + "\n" + selectedCourse + "\n" + selectedModeOfPayment;

private void generateQR() {
    String[] inputs = {
            et_firstname.getText().toString().trim(),
            et_middlename.getText().toString().trim(),
            et_lastname.getText().toString().trim(),
            et_gradeLevel.getText().toString().trim(),
            et_address.getText().toString().trim(),
            et_mobileNumber.getText().toString().trim(),
            et_nationality.getText().toString().trim(),
            et_email.getText().toString().trim(),
            et_age.getText().toString().trim(),
            et_semester.getText().toString().trim(),
            et_month_input.getText().toString().trim(),
            et_day_input.getText().toString().trim(),
            et_year_input.getText().toString().trim()
    };

    String selectedGender = genderSpinner.getSelectedItem().toString();
    String selectedCourse = courseSpinner.getSelectedItem().toString();
    String selectedModeOfPayment = modeOfPaymentSpinner.getSelectedItem().toString();

    for (int i = 0; i < inputs.length; i++) {
        inputs[i] = handleNull(inputs[i]);
    }

    String text = String.join("\n", inputs) + "\n" +
            selectedGender + "\n" +
            selectedCourse + "\n" +
            selectedModeOfPayment;
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
    private String handleNull(String input) {
        return input != null ? input : "";
    }
}
