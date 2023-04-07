package com.example.e_fir_user

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class user_login : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        var user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            updateUi(user)
        }
    }
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        mAuth=FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser

        var email=findViewById<EditText>(R.id.textView1)
        var password=findViewById<EditText>(R.id.textView2)

        val login=findViewById<Button>(R.id.button)

        val account_btn=findViewById<Button>(R.id.button)
        account_btn.setOnClickListener {
            startActivity(Intent(this,register_user::class.java))
        }

        val forgetpassword=findViewById<TextView>(R.id.lblforgetPass)
        forgetpassword.setOnClickListener {
            startActivity(Intent(this,forgot_password::class.java))
            Toast.makeText(this,"diracting to forgot password page",Toast.LENGTH_LONG).show()
        }

//        if(user != null){
//            updateUi(user)
//        }

        login.setOnClickListener{

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("user")
            var progressDialog = ProgressDialog(this)
            progressDialog.setTitle("It will take some time")
            progressDialog.setMessage("hello")
            progressDialog.show()


            mAuth!!.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        mAuth=FirebaseAuth.getInstance()
                        val user = mAuth!!.currentUser
                        updateUi(user)
                        progressDialog.dismiss()
                    }

                    else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
    private fun updateUi(user: FirebaseUser?) {
        if(user!!.isEmailVerified){
            startActivity(Intent(this,dashboard::class.java))
            finish()
        }
        else{
            user.sendEmailVerification()
            Toast.makeText(this,"Verify email first", Toast.LENGTH_LONG).show()
        }

    }
}