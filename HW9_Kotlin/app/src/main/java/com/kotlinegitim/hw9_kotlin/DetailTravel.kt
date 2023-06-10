package com.kotlinegitim.hw9_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DetailTravel : AppCompatActivity() {

    lateinit var title_txt : TextView
    lateinit var city_txt : TextView
    lateinit var notes_txt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_list2)


        title_txt = findViewById(R.id.title_detail)
        city_txt = findViewById(R.id.city_detail)
        notes_txt = findViewById(R.id.note_detail)

        var title = intent.getStringExtra("title")
        var city = intent.getStringExtra("city")
        var notes = intent.getStringExtra("note")

        title_txt.text = title
       // title_txt.movementMethod = ScrollingMovementMethod()

        city_txt.text = city

        notes_txt.text = notes
        notes_txt.movementMethod = ScrollingMovementMethod()



    }





}