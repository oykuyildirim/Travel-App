package com.kotlinegitim.hw9_kotlin

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kotlinegitim.hw9_kotlin.fragments.SignIn
import com.kotlinegitim.hw9_kotlin.fragments.SignUp
import java.util.*

class MainActivity : AppCompatActivity() {




    private lateinit var database: DatabaseReference

    lateinit var btn1: Button
    lateinit var btn2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference



       /* val user = Travels( "city memory",Travel("Ankara", "Bu gezide şu yerleri gezdim"))

        database.child("TravelNotes").child(user.email.toString()).setValue(user.travel)*/

        val signin = SignIn()
        val oneBundle = Bundle()
        oneBundle.putString("key1","sendData1")
        signin.arguments = oneBundle
        changeFragment(signin)

        btn1 = findViewById(R.id.signin)
        btn2 = findViewById(R.id.signup)

        btn1.setOnClickListener {
            oneBundle.putString("key1", UUID.randomUUID().toString() )
            signin.arguments = oneBundle
            changeFragment(signin)
        }
        btn2.setOnClickListener {
            val signup = SignUp()
            changeFragment(signup)
        }




    }
    fun changeFragment ( fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.homeFrameLayout, fragment)
        fragmentTransaction.commit()
    }

}