package com.example.e_fir_user

import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class ShoeStationAdapter(var ctx: Activity, var arlist: ArrayList<showstationmodel>) : RecyclerView.Adapter<ShoeStationAdapter.viewholder>() {
    val database = FirebaseDatabase.getInstance()


    inner class viewholder(v: View):RecyclerView.ViewHolder(v){
        var station_name : TextView = v.findViewById(R.id.Station_name)
        var Station_district : TextView = v.findViewById(R.id.Station_district)
        var miss_phone : Button =v.findViewById(R.id.btn_phone_complaint)
        var miss_vehicle : Button =v.findViewById(R.id.btn_vehicle_complaint)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=ctx.layoutInflater.inflate(R.layout.show_station,parent,false)
        return  viewholder(v)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.station_name.text=arlist[position].username
        holder.Station_district.text=arlist[position].district

        holder.miss_phone.setOnClickListener {
            var int1= Intent(ctx,missingPhone_details::class.java)
            ctx.startActivity(int1)
        }
        holder.miss_vehicle.setOnClickListener {
            var int2 = Intent(ctx,Vehicle_form::class.java)
            ctx.startActivity(int2)
        }
    }

    override fun getItemCount(): Int {
        return arlist.size
    }
}
