package com.itis.group11801.fedotova.mvpexample

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_email_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.container
import kotlinx.android.synthetic.main.fragment_sign_up.ti_email
import kotlinx.android.synthetic.main.fragment_sign_up.ti_password

class SignUpFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        btn_signup.setOnClickListener {
            val email = email.text.toString().trim { it <= ' ' }
            val password = password.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                ti_email.error = getString(R.string.error_email)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                ti_password.error = getString(R.string.error_password)
                return@setOnClickListener
            }
            if (password.length < 4) {
                ti_password.error = getString(R.string.error_password_length)
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                Toast.makeText(
                    requireContext(), TOAST_IFNO + task.isSuccessful, Toast.LENGTH_SHORT
                ).show()
                if (task.isSuccessful) {
                    findNavController().navigateUp()
                } else {
                    Snackbar.make(
                        container, AUTH_FAILED + task.exception!!,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private const val AUTH_FAILED = "Authentication Failed"
        private const val TOAST_IFNO = "createUserWithEmail:onComplete:"
    }
}
