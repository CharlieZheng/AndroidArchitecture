package com.cgf.androidarchitecture.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cgf.androidarchitecture.R
import com.cgf.androidarchitecture.pojo.Book


class AdapterBook<T>(val ctx: Context, val dataList: List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_book, parent, false)
        return H(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterBook<*>.H) holder.update(position)
    }

    private inner class H(view: View) : RecyclerView.ViewHolder(view) {

        private val pic: ImageView
        private val name: TextView
        private val authorName: TextView

        init {
            pic = view.findViewById<ImageView>(R.id.pic)
            name = view.findViewById<TextView>(R.id.name)
            authorName = view.findViewById<TextView>(R.id.authorName)
        }

        fun update(position: Int) {
            val item = dataList[position]
            if (item is Book) {
                name.setText(item.title)
                authorName.setText(item.author_intro)
            }

        }
    }
}