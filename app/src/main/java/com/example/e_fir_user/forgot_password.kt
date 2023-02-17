package com.example.e_fir_user

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class forgot_password : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        var mauth= FirebaseAuth.getInstance()
        val button27=findViewById<Button>(R.id.button27)
        button27.setOnClickListener{
            val emailAddress  = findViewById<EditText>(R.id.forget_email).text.toString()
            mauth!!.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Link send", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,user_login::class.java))
                        finish()
                    }

                }
        }

    }
}