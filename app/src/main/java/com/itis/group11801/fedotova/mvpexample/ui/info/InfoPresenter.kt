package com.itis.group11801.fedotova.mvpexample.ui.info

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.itis.group11801.fedotova.mvpexample.data.model.Info
import com.itis.group11801.fedotova.mvpexample.data.repository.InfoRepository
import com.itis.group11801.fedotova.mvpexample.data.repository.UserRepository
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class InfoPresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val infoRepository: InfoRepository,
) : MvpPresenter<InfoView>() {

    private var user: FirebaseUser? = userRepository.getCurrentUser()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user?.let { infoRepository.setListener(eventListener) } ?: viewState.navigateUp()
    }

    fun insertInfo(info: String) {
        presenterScope.launch { infoRepository.insertInfo(info) }
    }

    fun signOut() {
        userRepository.signOut()
        viewState.navigateUp()
    }

    fun crash() {
        userRepository.crash()
    }

    fun inflateAddDialog() {
        viewState.inflateAddDialog()
    }

    private val eventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val list = mutableListOf<Info>()
            dataSnapshot.children.forEach {
                val info = it.getValue(Info::class.java)
                info?.let { it1 -> list.add(it1) }
            }
            viewState.updateList(list)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            viewState.showToast(CANCELLED)
        }
    }

    companion object {
        const val CANCELLED = "Cancelled"
    }
}