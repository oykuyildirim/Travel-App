package com.kotlinegitim.hw9_kotlin.customadaptors

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kotlinegitim.hw9_kotlin.*

class ListTravelCustomAdaptor(private val context: Activity, private val resource: Int, private val objects: MutableList<Travels>) :
    ArrayAdapter<Travels>(context, resource, objects) {

    lateinit var title : TextView
    lateinit var city : TextView
    lateinit var notes : TextView
    private lateinit var database: DatabaseReference

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val root = context.layoutInflater.inflate(resource, null, true)

        title = root.findViewById(R.id.traveltitle)
        city = root.findViewById(R.id.travelCity)
        //notes = root.findViewById(R.id.travelNotes)



        val travels = objects.get(position)

        /*title.text = travels.title
        city.text = travels.travel?.city.toString()*/
        title.text = travels.traveldetail?.title.toString()
        city.text = travels.traveldetail?.travel?.city.toString()


        database = Firebase.database.reference

        root.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {


                val builder = AlertDialog.Builder(context)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->

                        database.child("TravelNotes").child(travels.uid.toString()).child(travels.traveldetail?.title.toString()).removeValue()

                        Toast.makeText(context,"Note is deleted !",Toast.LENGTH_LONG).show()
                        clear()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()

                return true

            }
        })



        root. setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View?) {

                var intent = Intent(context, DetailTravel::class.java)

                intent.putExtra("title",travels.traveldetail?.title.toString())
                intent.putExtra("city",travels.traveldetail?.travel?.city.toString())
                intent.putExtra("note",travels.traveldetail?.travel?.note.toString())

                context.startActivity(intent)

            }

        })


        return root
    }
}