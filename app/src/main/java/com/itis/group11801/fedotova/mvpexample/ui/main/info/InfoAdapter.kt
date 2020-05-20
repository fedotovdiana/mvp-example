package com.itis.group11801.fedotova.mvpexample.ui.main.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itis.group11801.fedotova.mvpexample.R
import com.itis.group11801.fedotova.mvpexample.data.model.Info
import kotlinx.android.synthetic.main.item_info.view.*

class InfoAdapter(
    private val list: MutableList<Info> = emptyList<Info>().toMutableList()
) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
        return InfoViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update(data: MutableList<Info>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    inner class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(info: Info) {
            itemView.tv_info.text = info.info
        }
    }
}