package com.kotlinegitim.hw9_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kotlinegitim.hw9_kotlin.customadaptors.ListTravelCustomAdaptor

class TravelNote : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    lateinit var travellist : List<Travel>
    lateinit var addBtn : Button
    //lateinit var showBtn : Button
    lateinit var title : EditText
    lateinit var city : EditText
    lateinit var notes : EditText

    lateinit var listTravel : ListView
    var travelDetail = mutableListOf< Travels>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_list)

        database = Firebase.database.reference

        //showBtn = findViewById(R.id.show)
        addBtn = findViewById(R.id.add)

        listTravel = findViewById(R.id.travelList)

        title = findViewById(R.id.titleNote)
        city = findViewById(R.id.cityNote)
        notes = findViewById(R.id.note)

        var useruid = intent.getStringExtra("uid")



        addBtn.setOnClickListener {

            val user = Travels(useruid.toString(),TravelDetail(title.text.toString(),
                Travel(city.text.toString(), notes.text.toString())
            ))

            Toast.makeText(this@TravelNote,"Note is added !",Toast.LENGTH_LONG).show()
            database.child("TravelNotes").child(useruid.toString()).child(user.traveldetail?.title.toString()).setValue(user.traveldetail?.travel)

            city.setText("")
            title.setText("")
            notes.setText("")
        }




    }

    override fun onResume() {

        var id = intent.getStringExtra("uid")

        println(id)



        val adaptor = ListTravelCustomAdaptor(this@TravelNote,R.layout.travel_custom_layout,travelDetail)


        database.child("TravelNotes").child(id.toString()).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adaptor.clear()
                travelDetail.removeAll(travelDetail)
                if ( snapshot.exists() ) {


                    snapshot.children.forEach {
                        /*val noteVal = it.getValue(TravelDetail::class.java)
                        val note = Travels(it.key!!, noteVal!!)*/

                        val city_travel = it.getValue(Travel::class.java)
                        val key = it.key

                        val travel = Travels(id,TravelDetail(key!!, city_travel!!))
                        println(travel)
                        travelDetail.add(travel)


                    }
                }


                listTravel.adapter = adaptor
                adaptor.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_LONG).show()
            }
        })


        super.onResume()
    }
}