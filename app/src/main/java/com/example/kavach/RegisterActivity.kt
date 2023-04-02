package com.example.kavach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

//    private lateinit var binding:MainActivity
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val msg = findViewById<TextView>(R.id.textView)
        val uName = findViewById<EditText>(R.id.Uname)
        val mail = findViewById<EditText>(R.id.Email)
        val pwd = findViewById<EditText>(R.id.pwd)
        val cPwd = findViewById<EditText>(R.id.cnfrmpwd)
        val submit = findViewById<Button>(R.id.button)


        fun signup() {

            val email = mail.text.toString()
            val password = mail.text.toString()

            if(email.isEmpty() && password.isEmpty()) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Enter valid crederntials to Sign-Up",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            else if(email.isEmpty()){
                Toast.makeText(this@RegisterActivity,
                    "Enter your E-mail",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            else if(password.isEmpty()){
                Toast.makeText(this@RegisterActivity,
                    "Enter your Password",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
//                        Toast.makeText(this@RegisterActivity,"Success!",Toast.LENGTH_SHORT).show()
//                        startActivity(intent)
                    } else {
                        Toast.makeText(this@RegisterActivity,"Registration Failed!",Toast.LENGTH_SHORT).show()
                    }
                }
        }

        submit.setOnClickListener() { signup() }

    }
}