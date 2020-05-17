package com.itis.group11801.fedotova.mvpexample

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        configureGoogleClient()
    }

    private fun initClickListeners() {
        btn_by_google.setOnClickListener { signIn() }
        btn_by_email.setOnClickListener { findNavController().navigate(R.id.nav_email_sign_in) }
        btn_by_phone.setOnClickListener { findNavController().navigate(R.id.nav_phone_sign_in) }
    }

    private fun configureGoogleClient() {
        val options =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), options)
        auth = FirebaseAuth.getInstance()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Toast.makeText(activity, account.id, Toast.LENGTH_LONG).show()
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(activity, GOOGLE_AUTH_FAILED, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        activity?.let {
            auth.signInWithCredential(credential).addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.nav_profile)
                } else {
                    Snackbar.make(requireView(), AUTH_FAILED, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 2403
        private const val AUTH_FAILED = "Authentication failed"
        private const val GOOGLE_AUTH_FAILED = "Google sign in failed"
    }
}


//216723690025-d11uue59hn4kg5jchvvemo8k1hr513qh.apps.googleusercontent.com
//kPwA8KFoXx3mGRsqA519rmQM