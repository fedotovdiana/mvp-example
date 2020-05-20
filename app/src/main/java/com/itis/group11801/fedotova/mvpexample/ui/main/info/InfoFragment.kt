package com.itis.group11801.fedotova.mvpexample.ui.main.info

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.itis.group11801.fedotova.mvpexample.App
import com.itis.group11801.fedotova.mvpexample.R
import com.itis.group11801.fedotova.mvpexample.data.model.Info
import kotlinx.android.synthetic.main.fragment_dailog.view.*
import kotlinx.android.synthetic.main.fragment_info.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class InfoFragment : MvpAppCompatFragment(), InfoView {

    private lateinit var adapter: InfoAdapter

    @Inject
    lateinit var presenterProvider: Provider<InfoPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.injectInfoFragment(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = InfoAdapter()
        rv.adapter = adapter
        initClickListeners()
    }

    private fun initClickListeners() {
        fab.setOnClickListener { presenter.inflateAddDialog() }
    }

    override fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    override fun updateList(list: MutableList<Info>) {
        adapter.update(list)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_crash -> presenter.crash()
            R.id.item_sign_out -> presenter.signOut()
            R.id.item_game -> presenter.openGame()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun inflateAddDialog() {
        val view = layoutInflater.inflate(R.layout.fragment_dailog, null)
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(TITLE)
                .setView(view)
                .setNegativeButton(BTN_CANCEL) { dialog, _ -> dialog.dismiss() }
                .setPositiveButton(BTN_ADD) { dialog, _ ->
                    if (view.et_dialog_info.text.isNullOrBlank()) {
                        view.et_dialog_info.error = ERROR
                    } else {
                        presenter.insertInfo(view.et_dialog_info.text.toString())
                    }
                    dialog.dismiss()
                }.create().show()
        }
    }

    companion object {
        const val TITLE = "New info"
        const val ERROR = "Blank field"
        const val BTN_CANCEL = "Cancel"
        const val BTN_ADD = "Add"
    }
}
