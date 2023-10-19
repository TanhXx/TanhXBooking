package com.example.booking.Model

import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
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
}