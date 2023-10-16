package com.example.booking.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.Model.getAll
import com.example.booking.R
import com.squareup.picasso.Picasso

class getAllAdapter(var context: Context, var ds: ArrayList<getAll>) :
    RecyclerView.Adapter<getAllAdapter.viewHolder>() {
    inner class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var img = itemView.findViewById<ImageView>(R.id.img)
        var sale = itemView.findViewById<TextView>(R.id.giamgia)
        var giagoc = itemView.findViewById<TextView>(R.id.giagoc)
        var giamgia = itemView.findViewById<TextView>(R.id.giadagiam)
        var checktym = itemView.findViewById<ImageView>(R.id.checktym)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var current = ds[position]

       /* var check : Boolean = current.is_answered
            if(check){
                holder.checktym.setImageResource(R.drawable.tym)
            }
            else{
                holder.checktym.setImageResource(R.drawable.like)
            }
        holder.checktym.setOnClickListener {
            check =!check
            if(check){
                holder.checktym.setImageResource(R.drawable.tym)
            }
            else{
                holder.checktym.setImageResource(R.drawable.like)
            }
        }*/



        holder.sale.text = "Giảm giá 30%"
        holder.giagoc.text = Html.fromHtml("<strike>$${current.price} </strike>",Html.FROM_HTML_MODE_LEGACY)
        var giadagiamf = current.price - (current.price * 30 / 100)
        holder.giamgia.text = giadagiamf.toString()
        Picasso.get().load(current.image_url).into(holder.img)

    }
}