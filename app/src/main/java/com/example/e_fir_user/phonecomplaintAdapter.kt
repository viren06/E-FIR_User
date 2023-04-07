package com.example.e_fir_user

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class phonecomplaintAdapter (var ctx: Activity, var arlist: ArrayList<showphonecomplaintModel>): RecyclerView.Adapter<phonecomplaintAdapter.viewholder>() {
    val database = FirebaseDatabase.getInstance()

    inner class viewholder(v: View): RecyclerView.ViewHolder(v){
        var user_name : TextView = v.findViewById(R.id.txt_name)
        var serial : TextView = v.findViewById(R.id.txt_mobilenumber)
        var pstatus : TextView = v.findViewById(R.id.txt_pstatus)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=ctx.layoutInflater.inflate(R.layout.user_phone_complaint_show,parent,false)
        return  viewholder(v)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.user_name.text=arlist[position].name
        holder.serial.text=arlist[position].mobile_number
        holder.pstatus.text=arlist[position].status


    }

    override fun getItemCount(): Int {
        return arlist.size
    }
}