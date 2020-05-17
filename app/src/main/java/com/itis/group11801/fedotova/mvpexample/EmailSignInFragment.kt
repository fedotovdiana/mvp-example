package com.itis.group11801.fedotova.mvpexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_email_sign_in.*
import kotlinx.android.synthetic.main.fragment_email_sign_in.container
import kotlinx.android.synthetic.main.fragment_sign_up.*

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
        btn_sign_in.setOnClickListener {
            auth.signInWithEmailAndPassword(et_email.text.toString(), et_password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(activity, "Success", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.nav_profile)
                    } else {
                        Snackbar.make(
                                container,
                                getString(R.string.auth_failed),
                                Snackbar.LENGTH_SHORT
                            )
                            .show()
                    }
                }
        }
    }

    companion object {
        private const val AUTH_SUCCESS = "Authentication success"
        private const val AUTH_FAILED = "Authentication failed"
    }
}
