package com.example.booking.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.FoodDetail
import com.example.booking.Model.FakeData
import com.example.booking.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class SpecialAdapter(var mcontext : Context, var ds: ArrayList<FakeData>) :
    RecyclerView.Adapter<SpecialAdapter.viewHolder>() {
    inner class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var calo = itemView.findViewById<TextView>(com.example.booking.R.id.calo)
        var img = itemView.findViewById<ImageView>(com.example.booking.R.id.imgmonan)
        var tt = itemView.findViewById<ImageView>(com.example.booking.R.id.tt)
        var tenmon = itemView.findViewById<TextView>(com.example.booking.R.id.tenmon)
        var giatien = itemView.findViewById<TextView>(com.example.booking.R.id.gia)
        var info = itemView.findViewById<ImageView>(com.example.booking.R.id.info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view =LayoutInflater.from(parent.context).inflate(com.example.booking.R.layout.item_phobien,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return ds.size
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var current = ds[position]



        var check : Boolean = current.is_answered
        if(check){
            holder.tt.setImageResource(R.drawable.loved)
        }
        else{
            holder.tt   .setImageResource(R.drawable.love)
        }
        holder.tt.setOnClickListener {
            check =!check
            if(check){
                holder.tt.setImageResource(R.drawable.loved)
            }
            else{
                holder.tt.setImageResource(R.drawable.love)
            }
        }
        holder.calo.text = "${current.owner.reputation.toString()} Calories"
        holder.tenmon.text = current.owner.user_id.toString()
        holder.giatien.text ="$${current.owner.reputation.toString()}"
        Picasso.get().load(current.owner.profile_image).into(holder.img)

        holder.info.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("calo",current.owner.reputation.toString())
            bundle.putString("tenmon",current.owner.user_id.toString())
            bundle.putString("giatien", current.owner.reputation.toString())
            bundle.putString("tym", current.is_answered.toString())
            bundle.putString("img", current.owner.profile_image)


            val foodDetail = FoodDetail()
            foodDetail.arguments = bundle
            (mcontext as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.maincontainer,foodDetail).addToBackStack(null).commit()
        }




    }

}