package com.kotlinegitim.hw9_kotlin.fragments

import androidx.fragment.app.Fragment
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kotlinegitim.hw9_kotlin.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var signUpBtn: Button
lateinit var emailUpEdit: EditText
lateinit var passwordUpEdit : EditText

class SignUp : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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

        /*val bundle = arguments
        val data = bundle!!.getString("key1")
        Log.d("data - ", data!!)*/
        val view: View = inflater.inflate(R.layout.signup_layout, container, false)

        signUpBtn = view.findViewById(R.id.login)
        emailUpEdit = view.findViewById(R.id.emailup)
        passwordUpEdit = view.findViewById(R.id.passwordup)

        auth = Firebase.auth

        signUpBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                 emailUpEdit.text.toString(),
                 passwordUpEdit.text.toString()
             )
                 .addOnCompleteListener(requireActivity()) { task ->
                     if (task.isSuccessful) {
                         Toast.makeText(
                             requireContext(),
                             "Authentication is successful !",
                             Toast.LENGTH_SHORT,
                         ).show()
                     } else {


                         Toast.makeText(
                             requireContext(),
                             "Authentication failed, there is a registered user with this email!",
                             Toast.LENGTH_SHORT,
                         ).show()

                     }
                 }
        }



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