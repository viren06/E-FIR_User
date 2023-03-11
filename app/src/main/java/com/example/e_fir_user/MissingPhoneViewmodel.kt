package com.example.e_fir_user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MissingPhoneViewmodel : ViewModel() {
    val database = FirebaseDatabase.getInstance()
    val data= MutableLiveData<userModel>()
    val myRef =database.getReference("phone")

    fun getdata(uid: String) : MutableLiveData<userModel>?{
        var myRef=database.getReference("user")
        val userprofile=object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value=dataSnapshot.getValue(userModel::class.java)!!
                data.value=value
                Log.d("TAG","value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG","Failed to read value.",error.toException())
            }

        }
        myRef.child(uid).addListenerForSingleValueEvent(userprofile)
        return data
    }

    fun savedata(uid:String,name:String,aadhar_number:String,email:String,address:String,relation:String,
                 last_seen_date:String,mobile_number:String,mobile_snumber:String,mobile_detail:String,status:String,username:String,phoneid:String,img:String,
                 stationanme:String,stationdistrict:String,stationid:String) {
        val phone=HashMap<String,Any>()
        phone["uid"]=uid
        phone["name"]=name
        phone["aadhar_number"]=aadhar_number
        phone["email"]=email
        phone["address"]=address
        phone["relation_with_user"]=relation
        phone["last_seen_date"]=last_seen_date
        phone["mobile_number"]=mobile_number
        phone["serial_number"]=mobile_snumber
        phone["mobile_details"]=mobile_detail
        phone["status"]=status
        phone["phoneid"]=phoneid
        phone["img"]=img
        phone["sname"]=stationanme
        phone["sdistrict"]=stationdistrict
        phone["sid"]=stationid
        myRef.child(phoneid).setValue(phone)
    }

}
