package com.itis.group11801.fedotova.mvpexample.ui.game.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import com.itis.group11801.fedotova.mvpexample.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : MvpAppCompatFragment(), HomeView {

    @Inject
    lateinit var presenterProvider: Provider<HomePresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectHomeFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_exit.setOnClickListener { presenter.exit() }
    }

    override fun navigateToMain() {
        startActivity(Intent(activity, MainActivity::class.java))
    }
}
