package com.example.e_fir_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.Address

class Vehicle_form : AppCompatActivity() {

    lateinit var Addhar_name : TextView
    lateinit var Aadhar_number :TextView
    lateinit var Email : TextView
    lateinit var Address: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_form)

        var isAllFieldsChecked = false
        var Addhar_name = findViewById<EditText>(R.id.et_name)
        var Aadhar_number = findViewById<EditText>(R.id.et_proof)
        var Email = findViewById<EditText>(R.id.et_email)
        var Address = findViewById<EditText>(R.id.et_details)

        //goto next form of missing vehicle
        var next_form=findViewById<Button>(R.id.owner_btn)
        next_form.setOnClickListener {

            isAllFieldsChecked = checkAllFields()
            when {
                TextUtils.isEmpty(Addhar_name.text.toString())-> Toast.makeText(this,"Enter Your Name", Toast.LENGTH_LONG).show()
                TextUtils.isEmpty(Email.text.toString())->Toast.makeText(this,"Enter Email", Toast.LENGTH_LONG).show()
                TextUtils.isEmpty(Address.text.toString())->Toast.makeText(this,"Enter Address", Toast.LENGTH_LONG).show()
                TextUtils.isEmpty(Aadhar_number.text.toString())->Toast.makeText(this,"Enter aadhar number", Toast.LENGTH_LONG).show()

                else -> {
                    var addhar_name = findViewById<EditText>(R.id.et_name).text.toString()
                    var aadhar_number = findViewById<EditText>(R.id.et_proof).text.toString()
                    var email = findViewById<EditText>(R.id.et_email).text.toString()
                    var address = findViewById<EditText>(R.id.et_details).text.toString()

                    //geeting station data
                    var pname = intent.getStringExtra("sname")
                    var pdistrict = intent.getStringExtra("district")
                    var pstationid = intent.getStringExtra("pid")

                    val exntent = Intent(this@Vehicle_form, vehicle_form_2::class.java)
                    exntent.putExtra("addharname", addhar_name.toString())
                    exntent.putExtra("aadharnumber", aadhar_number.toString())
                    exntent.putExtra("email", email.toString())
                    exntent.putExtra("address", address.toString())
                    exntent.putExtra("spname", pname.toString())
                    exntent.putExtra("district", pdistrict.toString())
                    exntent.putExtra("pid", pstationid.toString())
                    startActivity(exntent)
                }
            }

        }
    }
    private fun checkAllFields(): Boolean {
        var aadhar_number=findViewById<EditText>(R.id.et_proof)
        if (aadhar_number.length()<8){
            aadhar_number.setError("Aadhar number must be minimum 12 characters")
        }
        return true

    }
}