package com.example.e_fir_user

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class vehicle_form_2 : AppCompatActivity() {
    var username:String?=null
    var imaguri = "null"
    var firebaseuid= FirebaseAuth.getInstance().currentUser!!.uid
    lateinit var Registration_number:EditText
    lateinit var Last_seen_date:EditText
    lateinit var Type:EditText
    lateinit var Other_detail:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_form2)

        var iaadharName=intent.getStringExtra("addharname")
        var iaadhar_number=intent.getStringExtra("aadharnumber")
        var iEmial=intent.getStringExtra("email")
        var iaddress=intent.getStringExtra("address")

         Registration_number=findViewById<EditText>(R.id.et_reg_no)
         Last_seen_date=findViewById<EditText>(R.id.et_last_seen)
         Type=findViewById<EditText>(R.id.et_model)
         Other_detail=findViewById<EditText>(R.id.et_details)

        val viewModel=ViewModelProvider(this).get(MissingVehicleViewmodel::class.java)
        viewModel.getdata(firebaseuid)?.observe(this, androidx.lifecycle.Observer {
            username = it.username
        })
        var isAllFieldsChecked = false

        var missing_vehicle_complaint=findViewById<Button>(R.id.vehicle_btn)
        missing_vehicle_complaint.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("uploading Book Details")
                progressDialog.setMessage("Your book will upload in few seconds")
                progressDialog.show()

                                var firebase = FirebaseDatabase.getInstance().getReference("vehicle")
                                var key = firebase.push().key
                                viewModel.savedata(
                                    firebaseuid,
                                    iaadharName.toString(),
                                    iaadhar_number.toString(),
                                    iEmial.toString(),
                                    iaddress.toString(),
                                    Registration_number.text.toString(),
                                    Last_seen_date.text.toString(),
                                    Type.text.toString(),
                                    Other_detail.text.toString(),
                                    "processing",
                                    username!!,
                                    key.toString(),
                                    "null photo",

                                )
                                progressDialog.dismiss()
                                startActivity(Intent(this, dashboard::class.java))
                            }
                        }
    }

    private fun checkAllFields(): Boolean {

        if (Type.text.isEmpty()){
            Type.setError("Enter vehicle type.")
            return false
        }
        if (Registration_number.text.isEmpty()){
            Registration_number.setError("Enter registration number")
            return false
        }

        return true

    }
}