package com.example.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booking.R
import com.example.booking.databinding.FragmentChangePassfBinding

class ChangePassf : Fragment() {
    lateinit var binding: FragmentChangePassfBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePassfBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_change_passf, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}