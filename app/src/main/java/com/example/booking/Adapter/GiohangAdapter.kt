package com.example.booking.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.Model.Giohang
import com.squareup.picasso.Picasso

class GiohangAdapter(var mContext: Context, var ds: MutableList<Giohang>) :
    RecyclerView.Adapter<GiohangAdapter.Giohangholder>() {
    inner class Giohangholder (var itemView : View) : RecyclerView.ViewHolder(itemView){
        var tenmon = itemView.findViewById<TextView>(com.example.booking.R.id.tenmongh)
        var giatien = itemView.findViewById<TextView>(com.example.booking.R.id.giatiengh)
        var soluong = itemView.findViewById<TextView>(com.example.booking.R.id.soluong)
        var img = itemView.findViewById<ImageView>(com.example.booking.R.id.imgmonan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Giohangholder {
        var view = LayoutInflater.from(parent.context).inflate(com.example.booking.R.layout.item_giohang,parent,false)
        return Giohangholder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    override fun onBindViewHolder(holder: Giohangholder, position: Int) {
        var current = ds[position]

        holder.tenmon.text = current.tenmon
        holder.giatien.text = current.giatien.toString()
        holder.soluong.text = current.soluong.toString()
        Picasso.get().load(current.img).into(holder.img)
    }
}