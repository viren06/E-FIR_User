package com.example.e_fir_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Vehicle_form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_form)

        //goto next form of missing vehicle
        var next_form=findViewById<Button>(R.id.owner_btn)
        next_form.setOnClickListener {
            var addhar_name=findViewById<EditText>(R.id.et_name).text.toString()
            var aadhar_number=findViewById<EditText>(R.id.et_proof).text.toString()
            var email=findViewById<EditText>(R.id.et_email).text.toString()
            var address=findViewById<EditText>(R.id.et_details).text.toString()

            val exntent=Intent(this@Vehicle_form,vehicle_form_2::class.java)
            exntent.putExtra("addharname",addhar_name.toString())
            exntent.putExtra("aadharnumber",aadhar_number.toString())
            exntent.putExtra("email",email.toString())
            exntent.putExtra("address",address.toString())
            startActivity(exntent)

        }




    }
}