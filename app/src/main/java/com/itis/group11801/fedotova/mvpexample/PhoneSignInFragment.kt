package com.itis.group11801.fedotova.mvpexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_email_sign_in.*
import kotlinx.android.synthetic.main.fragment_phone_sign_in.*
import java.util.concurrent.TimeUnit

class PhoneSignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        btn_sign_in.setOnClickListener {
            Toast.makeText(activity, et_phone.text.toString(), Toast.LENGTH_LONG).show()
            activity?.let { activity ->
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    et_phone.text.toString(),
                    60,
                    TimeUnit.SECONDS,
                    activity,
                    callbacks
                )
            }
        }
        btn_verify.setOnClickListener { verifyPhoneNumberWithCode(et_code.text.toString()) }
    }

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(activity, VER_FAILED, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verifId: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                verificationId = verifId
                resendToken = forceResendingToken
                Toast.makeText(activity, CODE_SEND, Toast.LENGTH_LONG).show()
            }
        }

    private fun verifyPhoneNumberWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        activity?.let {
            auth.signInWithCredential(credential).addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, AUTH_SUCCESS, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.nav_profile)
                } else {
                    Snackbar.make(
                            container,
                            AUTH_FAILED,
                            Snackbar.LENGTH_SHORT
                        )
                        .show()
                }
            }
        }
    }

    companion object {
        private const val CODE_SEND = "Code send"
        private const val AUTH_FAILED = "Authentication failed"
        private const val AUTH_SUCCESS = "Authentication success"
        private const val VER_FAILED = "Verification Failed"

    }
}
