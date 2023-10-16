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
import com.example.booking.Model.FakeData
import com.example.booking.Model.Giamgia
import com.example.booking.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class SaleOff(var context: Context, var ds: ArrayList<FakeData>) :
    RecyclerView.Adapter<SaleOff.viewHolder>() {
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

        var check : Boolean = current.is_answered
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
        }


        holder.sale.text = "Giảm giá ${current.owner.reputation.toString()}%"
        holder.giagoc.text = Html.fromHtml("<strike>$${current.owner.reputation} </strike>",Html.FROM_HTML_MODE_LEGACY)
        var giadagiamf = current.owner.reputation * current.owner.reputation / 100
        holder.giamgia.text = giadagiamf.toString()

        Picasso.get().load(current.owner.profile_image).into(holder.img)

    }
}