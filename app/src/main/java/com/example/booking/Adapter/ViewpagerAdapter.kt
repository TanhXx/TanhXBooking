package com.example.booking.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.booking.Specialf
import com.example.booking.Homef
import com.example.booking.Campaignsf
import com.example.booking.Myprofilef
import com.example.booking.Notificationf
import com.example.booking.Love

class ViewpagerAdapter(fragment : Homef) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0){
            Notificationf()
        }else if (position == 1){
            Specialf()
        }else if (position == 2){
            Campaignsf()
        }else if (position == 3){
            Love()
        }else
            Myprofilef()
        }
    }
