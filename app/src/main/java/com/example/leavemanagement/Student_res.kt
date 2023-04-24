package com.example.leavemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Student_res : AppCompatActivity() {
    private lateinit var Ragp: RadioGroup
    private lateinit var submit: Button
    private lateinit var name: TextView
    private lateinit var id: TextView
    private lateinit var from: EditText
    private lateinit var to: EditText
    private lateinit var reason: EditText
    private lateinit var database: DatabaseReference
    private lateinit var database1:DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_res)
        Ragp = findViewById(R.id.rgp)
        submit = findViewById(R.id.button)
        name = findViewById(R.id.textView8)
        id = findViewById(R.id.textView4)
        from = findViewById(R.id.editTextTextPersonName)
        to = findViewById(R.id.editTextTextPersonName2)
        reason = findViewById(R.id.editTextTextPersonName3)
        val hlfd = findViewById<RadioButton>(R.id.radioButton)
        val fd = findViewById<RadioButton>(R.id.radioButton2)
//        database = Firebase.database.reference
        database = FirebaseDatabase.getInstance().getReference("Name")
        database1 = FirebaseDatabase.getInstance().getReference("Registration")


        auth = FirebaseAuth.getInstance()
        name.text = "Change"
        database.child(auth.currentUser!!.uid).get().addOnSuccessListener{
            if(it.exists()){
                val name2 = it.child(auth.currentUser!!.uid).value
                name.text = name2.toString()
            }
        }
        database1.child(auth.currentUser!!.uid).get().addOnSuccessListener{
            if(it.exists()){
                val id1 = it.child(auth.currentUser!!.uid).value
                id.text = id1.toString()
            }
        }


//        var reference: DatabaseReference =
//            FirebaseDatabase.getInstance().reference.child(
//                "Name"
//            )

//        var reference: DatabaseReference =
//            FirebaseDatabase.getInstance().getReference(auth.currentUser!!.uid)

//        reference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if (snapshot.exists()) {
//
//                    for (child in snapshot.children) {
//                        if(child.toString()==auth.currentUser!!.uid) {
//                            name.setText(child.getValue().toString())
//                            Log.d("name", child.getValue().toString())
//                            println(child.getValue().toString())
//                        }
//                    }
////                    val name1 = snapshot.child("Name").getValue(String::class.java)
////                    name.text = name1
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//                Toast.makeText(this@Student_res, "Error", Toast.LENGTH_SHORT).show()
//            }
//
//        })

//        var reference1: DatabaseReference =
//            FirebaseDatabase.getInstance().reference.child(
//                "Registration"
//            )
//
//        reference1.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                if (snapshot.exists()) {
//
//                    for (child in snapshot.children) {
//                        id.text = child.getValue().toString()
//
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//                Toast.makeText(this@Student_res, "Error", Toast.LENGTH_SHORT).show()
//            }
//
//        })


        Ragp.setOnCheckedChangeListener(

            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(
                    applicationContext, " On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
//

            }
        )
        submit.setOnClickListener {
            if(hlfd.isChecked&& to.text.toString()!=from.text.toString()){
                Toast.makeText(this, "Laving date and Arriving date should be same for Day Leave", Toast.LENGTH_SHORT).show()
            }
            else{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("from", from.text.toString())
            intent.putExtra("to", to.text.toString())
            intent.putExtra("reason", reason.text.toString())
            if(hlfd.isChecked){
                intent.putExtra("day", "Day Leave")
            }
            else if (fd.isChecked){
                intent.putExtra("day", "Night Leave")
            }
            startActivity(intent)
            finish()
        }
        }





    }
    }
