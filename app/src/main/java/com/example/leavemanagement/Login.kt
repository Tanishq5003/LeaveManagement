package com.example.leavemanagement

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

 lateinit var email:EditText
 lateinit var pass:EditText
 lateinit var login:Button
lateinit var auth: FirebaseAuth
lateinit var sign:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById(R.id.inputEmail)
        pass = findViewById(R.id.inputPassword)
        login = findViewById(R.id.btnlogin)
        auth = FirebaseAuth.getInstance()
        sign = findViewById(R.id.textViewSignUp)

        login.setOnClickListener {
            var txt_email:String = email.text.toString()
            var txt_password:String = pass.text.toString()
            login_user(txt_email, txt_password)
        }
        sign.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
    private fun login_user(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(this){ task->
            Toast.makeText(this , "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Student_res::class.java)
            startActivity(intent)
            finish()
        }
    }
    }
