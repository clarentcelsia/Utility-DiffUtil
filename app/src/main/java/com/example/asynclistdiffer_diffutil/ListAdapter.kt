package com.example.asynclistdiffer_diffutil

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.InputStream
import java.net.URL

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)
    //DiffUtil (utility)
    val itemDifferCallback = object: DiffUtil.ItemCallback<Items>(){
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

    }

    // to do job asynchronously
    val itemDiffer = AsyncListDiffer(this, itemDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        val itemIdx = itemDiffer.currentList[position]
        val ivImage = holder.itemView.findViewById<ImageView>(R.id.ivImage)
        val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)

        holder.itemView.apply {
            Glide.with(this).load(itemIdx.image).into(ivImage)
            tvName.text = itemIdx.name
        }
    }

    override fun getItemCount(): Int = itemDiffer.currentList.size
}