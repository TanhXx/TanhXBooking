package com.example.booking

import android.content.Context
import android.content.SharedPreferences
import com.example.booking.Model.Giohang
import com.google.gson.Gson

class GiohangsharePre(context : Context) {
    val name = "giohang"
    val key = "key_giohang"
    val sharedPreferences : SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    val gson = Gson()

    fun saveGiohang(giohang: Giohang){
        val giohangJson = gson.toJson(giohang)
        sharedPreferences.edit().putString(key,giohangJson).apply()
    }

    fun getGiohang() : Giohang? {
        val giohangJson = sharedPreferences.getString(key,null)
        return if(giohangJson != null){
            gson.fromJson(giohangJson, Giohang::class.java)
        }else{
            null
        }
    }
}