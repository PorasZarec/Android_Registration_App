package com.example.expensemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.FirebaseApp;

import com.example.expensemanager.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemsClick{

    ActivityMainBinding binding;
    //    Intent intent;
    private long income = 0, expense = 0;
    private ExpensesAdapater expensesAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        expensesAdapater = new ExpensesAdapater(this,this);
        binding.recycler.setAdapter(expensesAdapater);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));


        binding.addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddExpenseActivity.class);
                intent.putExtra("type", "Income");
                startActivity(intent);
            }
        });
        binding.addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddExpenseActivity.class);
                intent.putExtra("type", "Expense");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please");
        progressDialog.setMessage("Wait");
        progressDialog.setCancelable(false);
        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            FirebaseAuth.getInstance()
                    .signInAnonymously()
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressDialog.cancel();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.cancel();
                            Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        income=0; expense=0;
        getData();
    }


    private void getData() {
        FirebaseFirestore
                .getInstance()
                .collection("expenses")
                .whereEqualTo("uid",FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        expensesAdapater.clear();
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot ds:dsList){
                            ExpenseModel expenseModel = ds.toObject(ExpenseModel.class);
                            if (expenseModel.getType().equals("Income")){
                                income += expenseModel.getAmount();
                            }else {
                                expense += expenseModel.getAmount();
                            }
                            expensesAdapater.add(expenseModel);
                        }
                        setUpGraph();
                    }
                });
    }

    private void setUpGraph() {
        List<PieEntry> pieEntryList= new ArrayList<>();
        List<Integer> colorList = new ArrayList<>();
        if (income != 0) {
            pieEntryList.add(new PieEntry(income, "Income"));
            colorList.add(getResources().getColor(R.color.teal_700));
        }
        if (expense != 0) {
            pieEntryList.add(new PieEntry(expense, "Expense"));
            colorList.add(getResources().getColor(R.color.red));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, String.valueOf(income-expense));
        pieDataSet.setColors(colorList);
        PieData pieData = new PieData(pieDataSet);

        binding.pieChart.setData(pieData);
        binding.pieChart.invalidate();

    }

    @Override
    public void onClick(ExpenseModel expenseModel) {
        Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
        intent.putExtra("model",expenseModel);
        startActivity(intent);
    }
}