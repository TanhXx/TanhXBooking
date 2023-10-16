package com.example.booking.Model

object Singleton {
    var ds =  ArrayList<getAll>()

    fun addItems(items: List<getAll>){
        ds.addAll(items)
    }

    fun getItems() : ArrayList<getAll>{
        return ds
    }
}