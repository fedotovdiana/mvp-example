package com.itis.group11801.fedotova.mvpexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_dailog.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    private lateinit var database: DatabaseReference
    private lateinit var adapter: InfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        database = Firebase.database.reference.child(TABLE).child(user.uid)
        database.addValueEventListener(eventListener)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = InfoAdapter()
        rv.adapter = adapter
        initClickListeners()
    }

    private fun initClickListeners() {
        fab.setOnClickListener { addInfoItem() }
    }

    private val eventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val list = mutableListOf<Info>()
            dataSnapshot.children.forEach {
                val info = it.getValue(Info::class.java)
                info?.let { it1 -> list.add(it1) }
            }
            adapter.update(list)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Toast.makeText(activity, CANCELLED, Toast.LENGTH_LONG).show()
        }
    }

    private fun addInfoItem() {
        val view = layoutInflater.inflate(R.layout.fragment_dailog, null)
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(TITLE)
                .setView(view)
                .setNegativeButton(BTN_CANCEL) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(BTN_ADD) { dialog, _ ->
                    if (view.et_dialog_info.text.isNullOrBlank()) {
                        view.et_dialog_info.error = ERROR
                    } else {
                        val info =
                            Info(UUID.randomUUID().toString(), user.uid, view.et_dialog_info.text.toString())
                        database.child(info.id).setValue(info)
                    }
                    dialog.dismiss()
                }.create().show()
        }
    }

    companion object {
        const val TITLE = "New info"
        const val ERROR = "Blank field"
        const val TABLE = "info"
        const val BTN_CANCEL = "Cancel"
        const val BTN_ADD = "Add"
        const val CANCELLED = "Cancelled"
    }
}
