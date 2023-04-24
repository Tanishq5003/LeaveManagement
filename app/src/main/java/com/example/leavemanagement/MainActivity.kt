package com.example.leavemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.cert.CertPathValidatorException.BasicReason

class MainActivity : AppCompatActivity() {
    private lateinit var reg:TextView
    private lateinit var name:TextView
    private lateinit var from:TextView
    private lateinit var to:TextView
    private lateinit var reason: TextView
    private lateinit var type:TextView
    private lateinit var chkout:Button
    private lateinit var database: DatabaseReference
    private lateinit var database1: DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reg = findViewById(R.id.textView19)
        name = findViewById(R.id.textView20)
        from = findViewById(R.id.textView21)
        to  = findViewById(R.id.textView22)
        reason = findViewById(R.id.textView25)
        type = findViewById(R.id.textView23)
        chkout = findViewById(R.id.button2)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Name")
        database1 = FirebaseDatabase.getInstance().getReference("Registration")

        database.child(auth.currentUser!!.uid).get().addOnSuccessListener{
            if(it.exists()){
                val name2 = it.child(auth.currentUser!!.uid).value
                name.text = name2.toString()
            }
        }
        database1.child(auth.currentUser!!.uid).get().addOnSuccessListener{
            if(it.exists()){
                val id1 = it.child(auth.currentUser!!.uid).value
                reg.text = id1.toString()
            }
        }

        val from1:String = intent.getStringExtra("from").toString()
        val to1:String = intent.getStringExtra("to").toString()
        val reason1:String = intent.getStringExtra("reason").toString()
        val day:String = intent.getStringExtra("day").toString()

        from.text = from1
        to.text = to1
        reason.text = reason1
        type.text = day

        chkout.setOnClickListener {
            Toast.makeText(this, "Check-in Successful!!", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "You may now return to your hostel", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Login::class.java))
            finish()
        }

    }

    override fun onBackPressed() {

    }
}