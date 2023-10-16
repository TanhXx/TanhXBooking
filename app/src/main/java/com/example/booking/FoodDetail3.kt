package com.example.booking

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.booking.databinding.FragmentFoodDetail3Binding


class FoodDetail3 : Fragment() {
    private lateinit var binding : FragmentFoodDetail3Binding
    var count = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetail3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        option()

        binding.cong.setOnClickListener {
            count++
            binding.soluong.text = count.toString()
        }
        binding.tru.setOnClickListener {
            count --
            if (count<0){
                count = 0
                binding.soluong.text = count.toString()

            }else{
                binding.soluong.text = count.toString()
            }

        }

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Homef()).commit()
        }
    }

    private fun option() {
        var buttonList = arrayListOf(binding.hattieu,binding.cachua,binding.khoaitay)
        var selectted : Button? = null

        buttonList.forEach { button ->
            button.setOnClickListener {
                selectted?.setBackgroundColor(Color.parseColor("#EDE5E5"))
                button.setBackgroundColor(Color.parseColor("#B0E3EA"))
                selectted = button

            }
        }
    }

}