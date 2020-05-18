package com.itis.group11801.fedotova.mvpexample.ui.signin_email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import kotlinx.android.synthetic.main.fragment_email_sign_in.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class EmailSignInFragment : MvpAppCompatFragment(), EmailSignInView {

    @Inject
    lateinit var presenterProvider: Provider<EmailSignInPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectEmailSignInFragment(this)
        super.onCreate(savedInstanceState)
    }

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
        btn_to_sign_up.setOnClickListener { presenter.navigateToSignUp() }
        btn_reset_password.setOnClickListener { presenter.resetPassword(et_email.text.toString()) }
        btn_sign_in.setOnClickListener {
            presenter.signIn(
                et_email.text.toString(),
                et_password.text.toString()
            )
        }
    }

    override fun navigateToSignUp() {
        findNavController().navigate(R.id.nav_sign_up)
    }

    override fun navigateToInfo() {
        findNavController().navigate(R.id.nav_info)
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}
