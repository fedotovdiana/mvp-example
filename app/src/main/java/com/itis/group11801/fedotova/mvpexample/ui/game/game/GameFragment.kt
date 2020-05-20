package com.itis.group11801.fedotova.mvpexample.ui.game.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import kotlinx.android.synthetic.main.fragment_game.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class GameFragment : MvpAppCompatFragment(), GameView {

    @Inject
    lateinit var presenterProvider: Provider<GamePresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectGameFragment(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_play.setOnClickListener { presenter.startGame() }
    }

    override fun navigateToSettings() {
        findNavController().navigate(R.id.action_navigation_game_to_navigation_settings)
    }
}
