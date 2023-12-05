package com.example.booking

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.booking.Model.CartViewModel
import com.example.booking.Model.Giohang
import com.example.booking.databinding.FragmentFoodDetail3Binding


class FoodDetail3 : Fragment() {
    private lateinit var binding : FragmentFoodDetail3Binding
    var count = 0
    var calo = 0
    var tenmon = ""
    var giatien = 0
    var mota = ""
    var img = ""
    var topping : String? = null
    lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
    }
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
        tToan()
        showData()
        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Homef()).commit()
        }
        binding.giohang.setOnClickListener {
            if(topping!=null){
                var tongtien = binding.soluong.text.toString().toIntOrNull()!! * giatien
                var sl = binding.soluong.text.toString().toIntOrNull()
                var item = Giohang(tongtien, topping!!,giatien,tenmon,mota,sl!!,img)
                cartViewModel.addCartItem(item)
                parentFragmentManager.beginTransaction().replace(R.id.maincontainer,Homef()).commit()
            }else{
                Toast.makeText(requireContext(), "Bạn chưa chọn topping", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun showData() {
        tenmon = requireArguments().getString("tenmon")!!
        giatien = requireArguments().getInt("giatien")
        mota = requireArguments().getString("mota")!!
        calo = requireArguments().getInt("calo")
        img = requireArguments().getString("img")!!

        binding.calo.text = calo.toString() + "caloris"
        binding.mta.text = mota
        binding.tenmon.text = tenmon
        binding.gia.text = giatien.toString() + "$"
    }
    private fun tToan() {
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
    }

    private fun option() {
        var buttonList = arrayListOf(binding.hattieu,binding.cachua,binding.khoaitay)
        var selectted : Button? = null

        buttonList.forEach { button ->
            button.setOnClickListener {
                selectted?.setBackgroundColor(Color.parseColor("#EDE5E5"))
                button.setBackgroundColor(Color.parseColor("#B0E3EA"))
                selectted = button
                topping = button.text.toString()
            }
        }
    }

}