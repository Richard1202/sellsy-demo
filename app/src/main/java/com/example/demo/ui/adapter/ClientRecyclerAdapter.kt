package com.example.demo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.data.model.Client


class ClientRecyclerAdapter(private val context: Context, private var values: List<Client>) : RecyclerView.Adapter<ClientRecyclerAdapter.ViewHolder>() {

    private var clickListener: OnClickListener? = null
    private var loadMoreListener: OnLoadMoreListener? = null

    var isMoreDataAvailable:Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_client_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position >= getItemCount() - 1 && loadMoreListener!=null && isMoreDataAvailable){
            loadMoreListener?.onLoadMore();
        }

        val item = values[position]
        Glide.with(context).load(item.picture)
            .circleCrop()
            .into(holder.imageView)
        holder.nameView.text = item.fullName.trim()
        holder.addressView.text = item.joinDate.trim()
    }

    fun  getItem(position: Int): Client = values[position]

    override fun getItemCount(): Int = values.size

    fun setOnItemClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun update(data: List<Client>) {
        values = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageView: ImageView = view.findViewById(R.id.image)
        val nameView: TextView = view.findViewById(R.id.name)
        val addressView: TextView = view.findViewById(R.id.address)

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            if (v != null) {
                clickListener?.onItemClick(v,adapterPosition)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(v: View,position: Int)
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}