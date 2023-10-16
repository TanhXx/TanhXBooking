package com.example.booking

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.booking.databinding.FragmentFoodDetail2Binding
import com.example.booking.databinding.FragmentFoodDetail3Binding

class FoodDetail2 : Fragment() {
    private lateinit var binding: FragmentFoodDetail2Binding
    var count = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetail2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Homef()).commit()
        }

        Option()
        binding.cong.setOnClickListener {
            count ++
            binding.soluong.text  = count.toString()

        }
        binding.tru.setOnClickListener {
            count --
            if (count<0){
                count = 0
            }
            binding.soluong.text = count.toString()
        }

    }

    private fun Option() {
        var buttonList = listOf(binding.hattieu, binding.cachua,binding.khoaitay)
        var selectedbutton : Button? = null

        buttonList.forEach { button ->
            button.setOnClickListener {
                selectedbutton?.setBackgroundColor(Color.parseColor("#EDE5E5"))

                button.setBackgroundColor(Color.parseColor("#B0E3EA"))
                selectedbutton = button
            }
        }
    }

}

