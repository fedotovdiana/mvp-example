package com.itis.group11801.fedotova.mvpexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_email_sign_in.*

class EmailSignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_email_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        btn_to_sign_up.setOnClickListener { findNavController().navigate(R.id.nav_sign_up) }
        btn_reset_password.setOnClickListener { resetPassword() }
        btn_sign_in.setOnClickListener {
            auth.signInWithEmailAndPassword(et_email.text.toString(), et_password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, AUTH_SUCCESS, Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.nav_profile)
                    } else {
                        Snackbar.make(container, AUTH_FAILED, Snackbar.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun resetPassword() {
        val email = et_email.text.toString()
        if (email.isNotEmpty()) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(activity, SEND_SUCCESS, Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(activity, SEND_FAILED, Toast.LENGTH_LONG).show()
                }
            }
        } else {
            Toast.makeText(activity, INCORRECT_EMAIL, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val AUTH_SUCCESS = "Authentication success"
        private const val AUTH_FAILED = "Authentication failed"
        private const val SEND_SUCCESS = "Check email to reset password"
        private const val SEND_FAILED = "Failed"
        private const val INCORRECT_EMAIL = "Incorrect email"
    }
}
