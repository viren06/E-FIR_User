package com.example.e_fir_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class aadhar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aadhar)

        var iaadharName=intent.getStringExtra("addharname")
        var iaadhar_number=intent.getStringExtra("aadharnumber")
        var iEmial=intent.getStringExtra("email")
        var iaddress=intent.getStringExtra("address")
    }
}