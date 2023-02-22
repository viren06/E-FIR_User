package com.example.e_fir_user

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
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