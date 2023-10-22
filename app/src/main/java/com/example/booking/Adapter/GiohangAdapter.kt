package com.example.booking.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.Cart
import com.example.booking.Model.CartViewModel
import com.example.booking.Model.Giohang
import com.squareup.picasso.Picasso

class GiohangAdapter(var mContext: Cart, var ds: MutableList<Giohang>, var cartview: CartViewModel) :
    RecyclerView.Adapter<GiohangAdapter.Giohangholder>() {
    inner class Giohangholder (var itemView : View) : RecyclerView.ViewHolder(itemView){
        var mota = itemView.findViewById<TextView>(com.example.booking.R.id.mota)
        var giatien = itemView.findViewById<TextView>(com.example.booking.R.id.giatien)
        var soluong = itemView.findViewById<TextView>(com.example.booking.R.id.soluong)
        var img = itemView.findViewById<ImageView>(com.example.booking.R.id.imgmonan)
        val cong = itemView.findViewById<TextView>(com.example.booking.R.id.cong)
        val tru = itemView.findViewById<TextView>(com.example.booking.R.id.tru)
        var tenmon = itemView.findViewById<TextView>(com.example.booking.R.id.tenmon)
        var size = itemView.findViewById<TextView>(com.example.booking.R.id.size)
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
        Picasso.get().load(current.img).into(holder.img)
        holder.giatien.text = current.tongtien.toString() + "$"
        holder.tenmon.text = current.tenmon
        holder.mota.text = current.mota
        holder.soluong.text = current.soluong.toString()
        holder.size.text = "Size: " +current.size
        // Size - giá x1
        var x1 = current.tongtien/ current.soluong
        var count = current.soluong
        holder.cong.setOnClickListener {
            count ++
            holder.soluong.text = count.toString()
            var tt : Int = (count * x1)
            holder.giatien.text = tt.toString() + "$"
            cartview.update("${current.tenmon}",holder.soluong.text.toString().toIntOrNull()!!,tt )
            mContext.updateTotalPrice()

        }
        holder.tru.setOnClickListener {
            count --
            if (count<= 0){
                ds.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
            }else{
                holder.soluong.text = count.toString()
                var tt : Int = (count * x1)
                holder.giatien.text = tt.toString() + "$"
                cartview.update("${current.tenmon}",holder.soluong.text.toString().toIntOrNull()!!,tt )
                mContext.updateTotalPrice()
            }
        }

    }
}