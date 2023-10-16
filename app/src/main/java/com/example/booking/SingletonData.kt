package com.example.booking

import com.example.booking.Model.FakeData
import com.example.booking.Model.Listitem

object SingletonData {
    val ds = ArrayList<FakeData>()

    fun addItems(items : ArrayList<FakeData>){
        ds.addAll(items)
    }

    fun getItems() : ArrayList<FakeData>{
        return ds
    }
}