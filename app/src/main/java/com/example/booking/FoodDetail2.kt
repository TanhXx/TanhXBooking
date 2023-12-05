package com.example.booking

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.booking.Model.CartViewModel
import com.example.booking.Model.Giohang
import com.example.booking.databinding.FragmentFoodDetail2Binding
import com.example.booking.databinding.FragmentFoodDetail3Binding
import com.squareup.picasso.Picasso

class FoodDetail2 : Fragment() {
    private lateinit var binding: FragmentFoodDetail2Binding
    var count = 1
    var calo = 0
    var tenmon : String? = null
    var giatien = 0
    var tongtien = 0
    var TAG = "FoodDetail2"
    var tongtienpt = 0
    var img : String? = null
    var mota : String? = null
    var topping : String? = null
    var soluong = 1
    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
    }
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
            soluong = binding.soluong.text.toString().toIntOrNull()!!

        }
        binding.tru.setOnClickListener {
            count --
            if (count<1){
                count = 1
            }
            binding.soluong.text = count.toString()
            soluong = binding.soluong.text.toString().toIntOrNull()!!
        }
        showData()
        binding.giohang.setOnClickListener {
            if (topping == null){
                Toast.makeText(requireContext(), "Vui lòng chọn topping", Toast.LENGTH_SHORT).show()
            }else{
                tongTien()
                var item = Giohang(tongtien,"Topping: ${topping}",giatien,tenmon!!,mota!!,soluong,img!!)
                cartViewModel.addCartItem(item)
                val giohangpre = GiohangsharePre(requireContext())
                giohangpre.saveGiohang(item)
                parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Cart()).addToBackStack(null).commit()
            }

        }

    }

    private fun showData() {
        giatien = requireArguments().getInt("giatien")
        tenmon = requireArguments().getString("tenmon")
        calo = requireArguments().getInt("calo")
        img = requireArguments().getString("img")
        mota = requireArguments().getString("mota")

        binding.tenmon.text = tenmon
        binding.calo.text = calo.toString() +" Caloris"
        binding.giatien.text = giatien.toString() + "$"
        binding.mtct.text = mota
    }


    private fun Option() {
        var buttonList = listOf(binding.hattieu, binding.cachua,binding.khoaitay)
        var selectedbutton : Button? = null
        buttonList.forEach { button ->
            button.setOnClickListener {
                selectedbutton?.setBackgroundColor(Color.parseColor("#EDE5E5"))
                button.setBackgroundColor(Color.parseColor("#B0E3EA"))
                selectedbutton = button
                topping = button.text.toString()
                Log.d(TAG, "Option: ${topping}")
                tongTien()
            }


        }
    }
    private fun tongTien() {
        if (topping == "Hạt tiêu" || topping == "Cà chua" || topping == "Khoai tây") {
            tongtien = giatien * soluong + (giatien * soluong * 5 / 100)

        }
    }

}

