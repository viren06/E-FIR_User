package com.example.e_fir_user

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class register_user : AppCompatActivity() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null



    private var mAuth: FirebaseAuth? = null
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var username: EditText
    lateinit var phonenumber: EditText
    lateinit var age:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        mAuth = FirebaseAuth.getInstance();
        var user=FirebaseAuth.getInstance().currentUser
        if(user!=null)
            updateUi(user)
        var isAllFieldsChecked = false

        val registration =findViewById<Button>(R.id.button_reg)
        email=findViewById<EditText>(R.id.edt_email)
        username=findViewById<EditText>(R.id.edt_username)
        password=findViewById<EditText>(R.id.edt_pass)
        phonenumber=findViewById<EditText>(R.id.edt_phone)
        age=findViewById<EditText>(R.id.edt_age)

        registration.setOnClickListener{
            //check password
            var userfirebase: FirebaseUser?=null
            isAllFieldsChecked = checkAllFields()

            var progressDialog= ProgressDialog(this)
            progressDialog.setTitle("It will take some time")
            progressDialog.setMessage("Signing Up")
            progressDialog.show()

            when{
                TextUtils.isEmpty(email.text.toString())->email.error="Required"
                TextUtils.isEmpty(password.text.toString())-> Toast.makeText(this,"Enter password", Toast.LENGTH_LONG).show()
                TextUtils.isEmpty(username.text.toString())->Toast.makeText(this,"Enter username", Toast.LENGTH_LONG).show()

                else->{
                    mAuth!!.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                        .addOnCompleteListener(this){task ->
                            val user = mAuth!!.currentUser
                            if (user != null){
                                userfirebase= user

                                var hashMap=HashMap<String,Any>()
                                //firebase data entry
                                hashMap["username"]=username.text.toString()
                                hashMap["email"]=email.text.toString()
                                hashMap["password"]=password.text.toString()
                                hashMap["mobileNumber"]=phonenumber.text.toString()
                                hashMap["age"]=age.text.toString()

                                var myref= FirebaseDatabase.getInstance().getReference("User")
                                myref.child(user!!.uid.toString()).setValue(hashMap).addOnCompleteListener(this){
                                    Toast.makeText(this,"user created",Toast.LENGTH_SHORT).show()
                                    updateUi(user)
                                    progressDialog.dismiss()

                                    var int1= Intent(this@register_user, dashboard::class.java)
                                    int1.putExtra("username",username.toString())
                                    int1.putExtra("password",password.toString())
                                    startActivity(int1)
                                    finish()
                                }
                            }
                        }
                        .addOnCanceledListener {
                            Toast.makeText(this,"error try after some time",Toast.LENGTH_LONG).show()
                            progressDialog.dismiss()
                        }
                }
            }
            progressDialog.dismiss()
        }





    }
    private fun checkAllFields(): Boolean {
        var password1=findViewById<EditText>(R.id.edt_pass)
        if (password1.length()<8){
            password1.setError("password must be minimum 8 characters")
        }
        return true

    }
    private fun updateUi(user: FirebaseUser?) {
        if(user!!.isEmailVerified)
        {
            startActivity(Intent(this,dashboard::class.java))
            finish()
        }
        else{
            user.sendEmailVerification()
            Toast.makeText(this,"Verification mail sent your mail", Toast.LENGTH_LONG).show()
            startActivity(Intent(this,user_login::class.java))

        }

    }
}