package com.example.demo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.data.model.Client
import java.util.*
import kotlin.collections.ArrayList


class ClientRecyclerAdapter(private val context: Context, private var values: List<Client>) : RecyclerView.Adapter<ClientRecyclerAdapter.ViewHolder>(),
    Filterable {

    private var clickListener: OnClickListener? = null
    private var loadMoreListener: OnLoadMoreListener? = null

    var isMoreDataAvailable:Boolean = true

    private var filterResult: List<Client> = mutableListOf()

    init {
        filterResult = values
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_client_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position >= getItemCount() - 1 && loadMoreListener!=null && isMoreDataAvailable){
            loadMoreListener?.onLoadMore();
        }

        val item = filterResult[position]
        Glide.with(context).load(item.picture)
            .circleCrop()
            .into(holder.imageView)
        holder.nameView.text = item.fullName.trim()
        holder.addressView.text = item.joinDate.trim()
    }

    fun  getItem(position: Int): Client = filterResult[position]

    override fun getItemCount(): Int = filterResult.size

    fun setOnItemClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    fun update(data: List<Client>) {
        values = data
        filterResult = data
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterResult = values
                } else {
                    val resultList = ArrayList<Client>()
                    for (client in values) {
                        if (client.fullName.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(client)
                        }
                    }
                    filterResult = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterResult
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterResult = results?.values as List<Client>
                notifyDataSetChanged()
            }
        }
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