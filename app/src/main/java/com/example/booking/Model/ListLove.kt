package com.example.booking.Model

import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ListLove : ViewModel(){
    var ds = MutableLiveData(mutableListOf<getAll>())
     fun addItems(item:getAll) {
         ds.value?.add(item)
         ds.value = ds.value
    }

     fun remoteItems(item: getAll){
         ds.value?.remove(item)
         ds.value = ds.value
    }



}