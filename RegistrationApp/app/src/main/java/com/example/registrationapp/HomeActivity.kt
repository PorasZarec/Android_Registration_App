package com.example.registrationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.registrationapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val genderOptions = resources.getStringArray(R.array.gender_options)
        val genderAdapter = PromptArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        binding.genderSpinner.adapter = genderAdapter

        val courseOptions = resources.getStringArray(R.array.course_options)
        val courseAdapter = PromptArrayAdapter(this, android.R.layout.simple_spinner_item, courseOptions)
        binding.courseSpinner.adapter = courseAdapter

        // BUTTON REGISTER

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    class PromptArrayAdapter<T>(context: Context, resource: Int, objects: Array<out T>) :
        ArrayAdapter<T>(context, resource, objects) {

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            return if (position == 0) {
                val view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
                view.findViewById<TextView>(android.R.id.text1).setTextColor(ContextCompat.getColor(context, R.color.grey))
                view.findViewById<TextView>(android.R.id.text1).text = getItem(position).toString()
                view
            } else {
                super.getDropDownView(position, convertView, parent)
            }
        }

        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }
    }


}
