package com.example.kavach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kavach.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        val button = findViewById<Button>(R.id.loginBtn)

        val register = findViewById<TextView>(R.id.regMsg)

        register.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        fun performLogin(){
            val mail = findViewById<EditText>(R.id.mailLogin).toString()
            val pwd = findViewById<EditText>(R.id.pwdLogin).toString() 

            if(mail.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    "Enter valid credentials to Sign-Up",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            else if(mail.isEmpty()) {
                Toast.makeText(this@LoginActivity,
                    "Enter your E-mail",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            else if(pwd.isEmpty()) {
                Toast.makeText(this@LoginActivity,
                    "Enter your Password",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            auth.signInWithEmailAndPassword(mail, pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@LoginActivity,
                            "Login Successfull",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this@LoginActivity,
                            "Invalid Credentials",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this@LoginActivity,
                        "Authentication Failed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }

        button.setOnClickListener{
            performLogin()
        }

    }
}