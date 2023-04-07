package com.example.e_fir_user

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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

        //geeting station data from shoestationadapter using intent
        var pname=intent.getStringExtra("sname")
        var pdistrict=intent.getStringExtra("district")
        var pstationid=intent.getStringExtra("pid")

        val viewModel= ViewModelProvider(this).get(MissingPhoneViewmodel::class.java)
        viewModel.getdata(firebaseuid)?.observe(this, androidx.lifecycle.Observer {
            username = it.username
        })
        var isAllFieldsChecked = false

        complaint_phone.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked) {
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("uploading your missing phone  Details")
                progressDialog.setMessage("Your complaint will upload in few seconds")
                progressDialog.show()

                var firebase = FirebaseDatabase.getInstance().getReference("phone")
                var key = firebase.push().key
                viewModel.savedata(
                    firebaseuid,
                    musername.text.toString(),
                    maadharnumber.text.toString(),
                    memail.text.toString(),
                    maddress.text.toString(),
                    mrelation.text.toString(),
                    mlastdate.text.toString(),
                    mmobilenumber.text.toString(),
                    mserialnumber.text.toString(),
                    mmobilecompany.text.toString(),
                    "processing",
                    username!!,
                    key.toString(),
                    "null photo",
                    pname.toString(),
                    pdistrict.toString(),
                    pstationid.toString()
                )
                progressDialog.dismiss()
                startActivity(Intent(this, dashboard::class.java))
            }
        }


    }

    private fun checkAllFields(): Boolean {

        if (mserialnumber.text.length<15){
            mserialnumber.setError("Enter correct IMEI number")
            return false
        }
        if (maadharnumber.text.length<12){
            maadharnumber.setError("Enter aadhar number")
            return false
        }
        if (musername.text.isEmpty()){
            musername.setError("Enter aadhar name")
            return false
        }
        if (memail.text.isEmpty()){
            memail.setError("Enter email")
            return false
        }
        if (maddress.text.isEmpty()){
            maddress.setError("Enter your address")
            return false
        }
        if (mmobilenumber.text.length<10){
            mmobilenumber.setError("Enter correct mobile number")
            return false
        }
        if (mmobilecompany.text.isEmpty()){
            mmobilecompany.setError("Enter mobile company")
            return false
        }
        if (mlastdate.text.isEmpty()){
            mlastdate.setError("Enter last date")
            return false
        }
        if (mrelation.text.length<12){
            mrelation.setError("Enter your relation with user")
            return false
        }

        return true

    }
}