package com.example.e_fir_user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Edit_ProfileViewModel: ViewModel(){
    val database = FirebaseDatabase.getInstance()
    val data= MutableLiveData<userModel>()


    val myRef =database.getReference("user")

    fun getdata(uid: String) : MutableLiveData<userModel>?{
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
    fun savedataonly(uid: String,name:String,number:String,username:String,email:String,img:String){
        val user=HashMap<String,Any>()
        user["age"]=name
        user["uid"]=uid
        user["mobileNumber"]=number
        user["email"]=email
        user["username"]=username
        user["img"]=img

        myRef.child(uid).setValue(user)
    }


    fun savedata(uid: String, name: String, number: String, username: String, email: String, img: String) {
        val user=HashMap<String,Any>()
        user["age"]=name
        user["uid"]=uid
        user["mobileNumber"]=number
        user["email"]=email
        user["username"]=username
        user["img"]=img
        myRef.child(uid).setValue(user)

    }
}