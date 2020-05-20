package com.itis.group11801.fedotova.mvpexample.ui.game.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ScoreFragment : MvpAppCompatFragment(), ScoreView {

    @Inject
    lateinit var presenterProvider: Provider<ScorePresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectScoreFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }
}
