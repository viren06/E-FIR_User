package com.example.e_fir_user

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MissingVehicleViewmodel : ViewModel() {
    val database = FirebaseDatabase.getInstance()
    val data= MutableLiveData<userModel>()
    val myRef =database.getReference("vehicle")

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

    fun savedata(uid:String,name:String,aadhar_number:String,email:String,address:String,registration_number:String,
    last_seen_date:String,vehicle_type:String,other_detail:String,status:String,username:String,vehicleid:String,img:String,
    stationanme:String,stationdistrict:String,stationid:String) {
        val vehicle=HashMap<String,Any>()
        vehicle["uid"]=uid
        vehicle["name"]=name
        vehicle["aadhar_number"]=aadhar_number
        vehicle["email"]=email
        vehicle["address"]=address
        vehicle["registration_number"]=registration_number
        vehicle["last_seen_date"]=last_seen_date
        vehicle["vehicle_type"]=vehicle_type
        vehicle["other_details"]=other_detail
        vehicle["status"]=status
        vehicle["vehicleid"]=vehicleid
        vehicle["img"]=img
        vehicle["sname"]=stationanme
        vehicle["sdistrict"]=stationdistrict
        vehicle["sid"]=stationid
        myRef.child(vehicleid).setValue(vehicle)
    }

}
