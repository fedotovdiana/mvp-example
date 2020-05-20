package com.itis.group11801.fedotova.mvpexample.ui.main.signin_phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import kotlinx.android.synthetic.main.fragment_phone_sign_in.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class PhoneSignInFragment : MvpAppCompatFragment(), PhoneSignInView {

    @Inject
    lateinit var presenterProvider: Provider<PhoneSignInPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectPhoneSignInFragment(this)
        super.onCreate(savedInstanceState)
    }

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
        btn_send_code.setOnClickListener { presenter.signIn(et_phone.text.toString()) }
        btn_verify.setOnClickListener { presenter.verifyPhoneNumberWithCode(et_code.text.toString()) }
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}
