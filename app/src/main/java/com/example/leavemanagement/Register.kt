package com.example.leavemanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    lateinit var usrnme:EditText
    lateinit var regno:EditText
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var cnfrmpass:EditText
    private lateinit var register: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.inputEmail)
        pass = findViewById(R.id.inputPassword)
        register = findViewById(R.id.btnlogin)
        usrnme = findViewById(R.id.inputUsername)
        regno = findViewById(R.id.inputRegistrationNumber)
        cnfrmpass = findViewById(R.id.inputConfirmPassword)
        auth = FirebaseAuth.getInstance()
        database =
            Firebase.database.reference

        register.setOnClickListener {

            var txt_email:String = email.text.toString()
            var txt_password:String = pass.text.toString()
            var txt_cnfrm:String = cnfrmpass.text.toString()
            if (TextUtils.isEmpty(txt_email)|| TextUtils.isEmpty(txt_password)){
                Toast.makeText(this , "Empty Credential" , Toast.LENGTH_SHORT).show()
            }
            else if (txt_password.length<6){
                Toast.makeText(this , "Password should contain at least 6 characters" , Toast.LENGTH_SHORT).show()
            }
            else if (txt_password!=txt_cnfrm){
                Toast.makeText(this, "Passwords Don't Match", Toast.LENGTH_SHORT).show()
            }
            else{
                registerUser(txt_email , txt_password)
            }
        }

    }
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful()){
                var txt_name:String = usrnme.text.toString()
                var reg_no:String = regno.text.toString()
                Toast.makeText(this , "User Registered Successfully", Toast.LENGTH_SHORT).show()
                database.child("Name").child(Firebase.auth.currentUser!!.uid).setValue(txt_name)
                database.child("Registration").child(Firebase.auth.currentUser!!.uid).setValue(reg_no)

                var intent = Intent(this , Login::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this , "User Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}