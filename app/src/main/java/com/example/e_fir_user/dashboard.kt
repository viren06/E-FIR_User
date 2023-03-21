package com.example.e_fir_user

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_fir_user.databinding.ShowStationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class dashboard : AppCompatActivity() {
    val database= FirebaseDatabase.getInstance()
    private var mauth: FirebaseAuth?=null
    lateinit var showstaion : RecyclerView
    var data= arrayListOf<showstationmodel>()
    var displaylist= arrayListOf<showstationmodel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

//        var missing_vehicle=findViewById<Button>(R.id.btn_vehicle)
//        missing_vehicle.setOnClickListener {
//            startActivity(Intent(this,Vehicle_form::class.java))
//            finish()
//        }

//        var missing_phone=findViewById<Button>(R.id.btn_missingpphone)
//        missing_phone.setOnClickListener {
//            startActivity(Intent(this,missingPhone_details::class.java))
//            finish()
//        }

        val myref=database.getReference("policestation")

        //show data first time
        shopdataupdate(myref)

        var lblcity=findViewById<TextView>(R.id.lblselectcity)
        lblcity.setOnClickListener {
            val builder= Dialog(this)
            //builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
            builder.setCancelable(false)
            builder.setTitle("Select City")
            builder.setContentView(R.layout.select_city)
            var btn=builder.findViewById<Button>(R.id.btncity)
            val city1=builder.findViewById<EditText>(R.id.edtcity)

            btn.setOnClickListener {
                var city=city1.text.toString()
                lblcity.text="City : "+city
                if(city!="") {
                    Toast.makeText(baseContext, city.toString(), Toast.LENGTH_SHORT).show()
                    shopdataupdate(myref, city)
                }
                else
                    Toast.makeText(baseContext, city.toString(), Toast.LENGTH_SHORT).show()
                builder.dismiss()
            }
            builder.show()

        }

    }

    private fun shopdataupdate(myref: DatabaseReference,city: String?=null) {

        data.clear()
        displaylist.clear()
        showstaion=findViewById(R.id.rv_showstation)

        myref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data.clear()
                displaylist.clear()
                var ad= ShoeStationAdapter(this@dashboard,displaylist)

                    for (v in datasnapshot.children) {
                        val value = v.getValue(showstationmodel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                            if(city!=null){
                                if(value.district.equals(city,true)) {
                                    data.add(value)
                                }
                            }
                            else
                                data.add(value)
                        }
                    }
                    displaylist.addAll(data)
                    showstaion.adapter = ad

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        showstaion.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }

    //otion menue
    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    //option menue commands and next activity
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("LOGOUT")
                builder.setMessage("Do You want to logout from Applicatiom?")
                builder.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int ->
                    finish()
                    startActivity(Intent(this, user_login::class.java))
                })
                builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
                builder.show()
                true
            }
            R.id.edit->{
                startActivity(Intent(this, editProfile::class.java))
                true
            }
            R.id.show->{
                startActivity(Intent(this, user_complaint::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}