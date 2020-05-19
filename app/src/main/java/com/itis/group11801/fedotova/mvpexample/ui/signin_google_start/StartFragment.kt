package com.itis.group11801.fedotova.mvpexample.ui.signin_google_start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import kotlinx.android.synthetic.main.fragment_start.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class StartFragment : MvpAppCompatFragment(), StartView {

    @Inject
    lateinit var presenterProvider: Provider<StartPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var advRequest: AdRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectStartFragment(this)
        super.onCreate(savedInstanceState)
        MobileAds.initialize(activity)
        advRequest = AdRequest.Builder().build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adv.loadAd(advRequest)
        initClickListeners()
    }

    private fun initClickListeners() {
        btn_by_google.setOnClickListener { presenter.signIn() }
        btn_by_email.setOnClickListener { presenter.navigateToEmail() }
        btn_by_phone.setOnClickListener { presenter.navigateToPhone() }
        tv_to_info.setOnClickListener { presenter.navigateToInfo() }
    }

    override fun navigateToInfo() {
        findNavController().navigate(R.id.nav_info)
    }

    override fun navigateToEmail() {
        findNavController().navigate(R.id.nav_email_sign_in)
    }

    override fun navigateToPhone() {
        findNavController().navigate(R.id.nav_phone_sign_in)
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    override fun signIn() {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val signInIntent = GoogleSignIn.getClient(requireActivity(), options).signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                presenter.firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                presenter.onException()
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 2403
    }
}

//216723690025-d11uue59hn4kg5jchvvemo8k1hr513qh.apps.googleusercontent.com
//kPwA8KFoXx3mGRsqA519rmQM