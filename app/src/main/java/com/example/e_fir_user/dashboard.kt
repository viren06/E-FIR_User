package com.example.e_fir_user

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_fir_user.databinding.ShowStationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class dashboard : AppCompatActivity() {
    val database= FirebaseDatabase.getInstance()
    private var mauth: FirebaseAuth?=null
    lateinit var showstaion : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var missing_vehicle=findViewById<Button>(R.id.btn_vehicle)
        missing_vehicle.setOnClickListener {
            startActivity(Intent(this,Vehicle_form::class.java))
            finish()
        }

        var missing_phone=findViewById<Button>(R.id.btn_missingpphone)
        missing_phone.setOnClickListener {
            startActivity(Intent(this,missingPhone_details::class.java))
            finish()
        }

        var data= arrayListOf<showstationmodel>()
        showstaion=findViewById(R.id.rv_showstation)

        val myref=database.getReference("policestation")
        myref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data.clear()
                var ad= ShoeStationAdapter(this@dashboard,data)
                if(datasnapshot.exists()) {
                    for (v in datasnapshot.children) {
                        val value = v.getValue(showstationmodel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                            data.add(value)
                        }
                    }
                    showstaion.adapter = ad
                }
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
            else -> super.onOptionsItemSelected(item)

        }
    }
}