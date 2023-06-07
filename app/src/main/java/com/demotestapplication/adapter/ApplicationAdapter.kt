package com.demotestapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demotestapplication.R
import com.demotestapplication.model.App

class ApplicationAdapter : RecyclerView.Adapter<ApplicationAdapter.MyViewHolder>() {
    var itemList = ArrayList<App>()
    fun updateData(items: ArrayList<App>){
        this.itemList = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val ivAvatar = view.findViewById<ImageView>(R.id.profile_image)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val status = view.findViewById<Switch>(R.id.switchapp)
        fun bind(data : App){
            tvName.text = data.app_name
            status.isChecked = data.status == "Active"
            Glide.with(itemView.context)
                .load(data.app_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivAvatar);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_item_cell, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList.get(position))
    }

    override fun getItemCount(): Int {
        return itemList.size

    }
}