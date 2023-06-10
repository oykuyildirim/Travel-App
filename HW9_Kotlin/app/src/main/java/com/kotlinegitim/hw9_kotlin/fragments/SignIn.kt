package com.kotlinegitim.hw9_kotlin.fragments

import android.app.AlertDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kotlinegitim.hw9_kotlin.R
import com.kotlinegitim.hw9_kotlin.TravelNote


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignIn : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var signInBtn: Button
    lateinit var emailEdit: EditText
    lateinit var passwordEdit : EditText
    lateinit var passChange : TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.signin_layout, container, false)

        emailEdit = view.findViewById(R.id.email)
        passwordEdit = view.findViewById(R.id.password)
        signInBtn = view.findViewById(R.id.register)
        passChange = view.findViewById(R.id.updatePass)



        auth = Firebase.auth


        signInBtn.setOnClickListener {


            auth.signInWithEmailAndPassword(
                emailEdit.text.toString(),
                passwordEdit.text.toString()
            )
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {

                        Log.d(ContentValues.TAG, "signInWithEmail:success")
                        val user = auth.currentUser

                        var intent = Intent(requireActivity(),TravelNote::class.java)
                        if (user != null) {
                            intent.putExtra("uid", user.uid)
                        }
                        startActivity(intent)
                        requireActivity().finish()


                    } else {

                        Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            requireActivity(),
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }
        }


        passChange.setOnClickListener(View.OnClickListener{

            val builder = AlertDialog.Builder(context)
            val reset_alert: View = layoutInflater.inflate(R.layout.reset_alert_layout, null)
            builder.setView(reset_alert)
            builder.setTitle("RESET YOUR PASSWORD")
            builder.setMessage("Do you want to reset your password ?")
                .setCancelable(false)
                .setPositiveButton("RESET PASSWORD") { dialog, id ->


                    val userEmail= reset_alert.findViewById<EditText>(R.id.email_reset)

                    Firebase.auth.sendPasswordResetEmail(userEmail.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                Toast.makeText(requireActivity(),"Email is sended, please look your inbox or spam !",Toast.LENGTH_LONG).show()
                            }
                        }


                }
                .setNeutralButton("No") { dialog, id ->


                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

        })



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment One.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUp().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}