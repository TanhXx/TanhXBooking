package com.example.booking.Model

import androidx.lifecycle.ViewModel

open class CartViewModel : ViewModel() {
    val cartItems: MutableList<Giohang> = mutableListOf()

    fun addCartItem(item: Giohang) {
        cartItems.add(item)
    }
    fun removeDuplicateTenmon() {
        val uniqueTenmonList = HashSet<String>()
        val iterator = cartItems.iterator()

        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!uniqueTenmonList.add(item.tenmon)) {
                iterator.remove()
            }
        }
    }

    fun update (tenmon: String, soluongmoi : Int, tongtienmoi : Int){
        val item =  cartItems.find { it.tenmon == tenmon }
        item?.soluong = soluongmoi
        item?.tongtien = tongtienmoi
    }
    fun updatett (tenmon: String, tongtienmoi : Int){
        val item =  cartItems.find { it.tenmon == tenmon }
        item?.tongtien = tongtienmoi
    }
}