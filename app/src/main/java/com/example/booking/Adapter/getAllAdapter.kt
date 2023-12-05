package com.example.booking.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.booking.FoodDetail2
import com.example.booking.FoodDetail3
import com.example.booking.Model.ListLove
import com.example.booking.Model.Singleton
import com.example.booking.Model.getAll
import com.example.booking.R
import com.squareup.picasso.Picasso

class getAllAdapter(var context: Context, var ds: ArrayList<getAll>) :
    RecyclerView.Adapter<getAllAdapter.viewHolder>() {
    private var isClickable = true

    fun setClickable(clickable : Boolean){
        isClickable = clickable
    }

    inner class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var img = itemView.findViewById<ImageView>(R.id.img)
        var sale = itemView.findViewById<TextView>(R.id.giamgia)
        var giagoc = itemView.findViewById<TextView>(R.id.giagoc)
        var giamgia = itemView.findViewById<TextView>(R.id.giadagiam)
        var checkbox = itemView.findViewById<CheckBox>(R.id.checktym)
        var tenmon = itemView.findViewById<TextView>(R.id.tenmon)
        var mota = itemView.findViewById<TextView>(R.id.mota)
        var fullscreen = itemView.findViewById<RelativeLayout>(R.id.fullscreen)

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
        Log.d("check", "onBindViewHolder: ${current.check}")
        holder.sale.text = "Giảm giá 30%"
        holder.giagoc.text = Html.fromHtml("<strike>$${current.price} </strike>",Html.FROM_HTML_MODE_LEGACY)
        var giadagiamf = "${current.price - (current.price * 30 / 100)  }"
        holder.giamgia.text = giadagiamf +"$"
        Picasso.get().load(current.image_url).into(holder.img)
        var check =  holder.checkbox.isChecked
        holder.mota.text = current.description
        holder.tenmon.text = current.name
        holder.checkbox.isChecked = current.check
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            current.check = isChecked
            val originalItem = Singleton.getItems().find { it.id == current.id }
            originalItem?.check = isChecked
        }
        var bundle = Bundle()
        var foodDetail3 = FoodDetail3()
        var foodDetail2 = FoodDetail2()
        holder.checkbox.setOnClickListener {
            if(!isClickable){
               holder.checkbox.isChecked = true
                Toast.makeText(context, "Bạn không thể xóa ở màn này!", Toast.LENGTH_SHORT).show()
            }
        }
        holder.fullscreen.setOnClickListener {
            if(!isClickable){
                foodDetail3.arguments = bundle
                bundle.putInt("calo", current.calories.toString().toIntOrNull()!!)
                bundle.putString("tenmon",current.name)
                bundle.putInt("giatien", giadagiamf.toInt())
                bundle.putString("img", current.image_url)
                bundle.putString("mota", current.description)
                bundle.putBoolean("check" , holder.checkbox.isChecked )
                (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.maincontainer,foodDetail3).addToBackStack(null).commit()
            } else if(isClickable){
                    foodDetail2.arguments = bundle
                    bundle.putInt("calo", current.calories)
                    bundle.putString("tenmon",current.name)
                    bundle.putInt("giatien", giadagiamf.toInt())
                    bundle.putString("img", current.image_url)
                    bundle.putString("mota", current.description)
                    bundle.putBoolean("check" , holder.checkbox.isChecked )
                    (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.maincontainer, foodDetail2).addToBackStack(null).commit()
            }
        }


    }




}