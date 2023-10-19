package com.example.booking

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.booking.Adapter.SpecialAdapter
import com.example.booking.databinding.FragmentFoodDetailBinding
import com.squareup.picasso.Picasso


class FoodDetail : Fragment() {
   var Size : String? = null
    var count = 1
    var img : String? =null
    var thanhtien = 0
   lateinit var binding : FragmentFoodDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Showdata()
        Changecolorsize()
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
            parentFragmentManager.beginTransaction().replace(R.id.maincontainer,Homef()).commit()
        }

        binding.giohang.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("soluong", binding.soluong.text.toString())
            bundle.putString("size", Size)
            bundle.putString("tenmon", binding.tenmon.text.toString())
            bundle.putString("gia", binding.gia.text.toString())
            bundle.putString("img", img)

            val cartFragment = Cart()
            cartFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.maincontainer, cartFragment,"CartFragmentTag")
                .commit()

        }




    }

    private fun Changecolorsize() {
        val buttonList = listOf(binding.sizel,binding.sizes,binding.sizem)
        var selectedbutton : Button? = null

        buttonList.forEach { button ->
            button.setOnClickListener {
                selectedbutton?.setBackgroundColor(Color.parseColor("#EDE5E5"))

                button.setBackgroundColor(Color.parseColor("#B0E3EA"))
                selectedbutton = button



            }
        }
    }

    private fun Showdata() {
        var giatien = arguments?.getString("giatien")
        var tenmon = arguments?.getString("tenmon")
        var calo = arguments?.getString("calo")
        var tym = arguments?.getString("tym").toBoolean()
        img = arguments?.getString("img")

        binding.tenmon.text = tenmon
        binding.calo.text = "${calo} Calories"
        binding.gia.text = "$${giatien}"
        if (tym){
            binding.tym.setImageResource(R.drawable.loved)
        }else{
            binding.tym.setImageResource(R.drawable.love)
        }
        Picasso.get().load(img).into(binding.imgmonan)
    }


}