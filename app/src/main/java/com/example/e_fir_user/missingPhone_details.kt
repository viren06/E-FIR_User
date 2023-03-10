package com.example.e_fir_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class missingPhone_details : AppCompatActivity() {
    var username:String?=null
    var imaguri = "null"
    var firebaseuid= FirebaseAuth.getInstance().currentUser!!.uid

    lateinit var  musername:EditText
    lateinit var maadharnumber:EditText
    lateinit var memail:EditText
    lateinit var maddress:EditText
    lateinit var mmobilenumber:EditText
    lateinit var mmobilecompany:EditText
    lateinit var mlastdate:EditText
    lateinit var mrelation:EditText
    lateinit var mserialnumber:EditText
    lateinit var complaint_phone:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missing_phone_details)

        musername=findViewById(R.id.et_name)
        maadharnumber=findViewById(R.id.et_proof)
        memail=findViewById(R.id.et_email)
        maddress=findViewById(R.id.et_address)
        mmobilenumber=findViewById(R.id.et_missingPh)
        mmobilecompany=findViewById(R.id.et_mobileModel)
        mlastdate=findViewById(R.id.et_lastSeen_phone)
        mrelation=findViewById(R.id.et_relation)
        mserialnumber=findViewById(R.id.et_serialnumber)
        complaint_phone=findViewById(R.id.owner_btn)


    }
}