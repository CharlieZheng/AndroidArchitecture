package com.ljp.androidarchitecture.adapter

import android.content.Context

class AdapterBook<T>(val ctx: Context, val dataList: List<T>) /*: RecyclerView.Adapter<RecyclerView.ViewHolder>()*/ {

/*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.abc_action_bar_title_item,parent,  false)
        return H(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is H) holder.update(position)
    }

    private class H ( view: View): RecyclerView.ViewHolder(view) {
        public fun update(position: Int) {}
    }*/
}