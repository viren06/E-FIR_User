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

class vehiclecomplaintAdapter  (var ctx: Activity, var arlist: ArrayList<showvehiclecomplaintModel>): RecyclerView.Adapter<vehiclecomplaintAdapter.viewholder>()  {
    val database = FirebaseDatabase.getInstance()

    inner class viewholder(v: View): RecyclerView.ViewHolder(v){
        var user_name : TextView = v.findViewById(R.id.txt_name)
        var vehicle_number: TextView = v.findViewById(R.id.txt_complaintfor)
        var cstatus : TextView = v.findViewById(R.id.txt_status)
//        var user_mail : TextView =v.findViewById(R.id.txt_email)
//        var register_num : TextView =v.findViewById(R.id.txt_registration_number)
//        var rc_num : TextView =v.findViewById(R.id.txt_rcnumber)
//        var relation_with_usr : TextView =v.findViewById(R.id.txt_relation)
//        var vdetails : TextView =v.findViewById(R.id.vehicle_type_model)
//        var lastSdate : TextView =v.findViewById(R.id.txt_lastdate)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=ctx.layoutInflater.inflate(R.layout.user_complaint_show,parent,false)
        return  viewholder(v)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.user_name.text=arlist[position].name
        holder.vehicle_number.text=arlist[position].registration_number
        holder.cstatus.text=arlist[position].status
//        holder.user_mail.text=arlist[position].email
//        holder.register_num.text=arlist[position].registration_number
//        holder.rc_num.text=arlist[position].other_details
//        holder.relation_with_usr.text=arlist[position].relation_with_user
//        holder.vdetails.text=arlist[position].vehicle_type
//        holder.lastSdate.text=arlist[position].last_seen_date


    }

    override fun getItemCount(): Int {
        return arlist.size
    }

}