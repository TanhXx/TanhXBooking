package com.example.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booking.databinding.FragmentSettingfBinding


class Settingf : Fragment() {
lateinit var binding: FragmentSettingfBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingfBinding.inflate(inflater,container,false)
        return binding.root
    }




}