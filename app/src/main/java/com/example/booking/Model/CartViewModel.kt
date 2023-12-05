package com.example.booking.Model

import androidx.lifecycle.ViewModel

open class CartViewModel : ViewModel() {
    val cartItems: MutableList<Giohang> = mutableListOf()
    fun addCartItem(item: Giohang) {
        cartItems.add(item)
    }
    fun update (tenmon: String, soluongmoi : Int, tongtienmoi : Int){
        val item =  cartItems.find { it.tenmon == tenmon }
        item?.soluong = soluongmoi
        item?.tongtien = tongtienmoi
    }

}