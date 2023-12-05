package com.example.booking

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.booking.Model.CartViewModel
import com.example.booking.Model.Giohang
import com.example.booking.Model.Singleton
import com.example.booking.databinding.FragmentFoodDetailBinding
import com.squareup.picasso.Picasso
import java.lang.Boolean
import kotlin.String
import kotlin.arrayOf

class FoodDetail : Fragment() {
    var TAG = "detail"
    var tongtienmon = 0
    var Size: String? = null
    var count = 1
    var img: String? = null
    var tongtienpt = 0
    var giatien = 0
    var calo = 0
    var soluong =1
    var mota : String? = null
    var tenmon: String? = null
    lateinit var binding: FragmentFoodDetailBinding
    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ${Singleton.getItems()}")
        super.onViewCreated(view, savedInstanceState)

        Showdata()
        Changecolorsize()
        TT()

        binding.giohang.setOnClickListener {
            if(Size == null){
                Toast.makeText(requireContext(), "Vui lòng chọn kích thước Pizza", Toast.LENGTH_SHORT).show()
            }else {
                Log.d(TAG, "${tongtienmon}-${Size}-${giatien}-${tenmon}-${mota}-${img}- ${soluong}")
                val item = Giohang(tongtienpt,"Size: ${Size}",giatien, "${tenmon}", "${mota}", soluong, "${img}")
                cartViewModel.addCartItem(item)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.maincontainer, Cart(), "CartFragmentTag")
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

    private fun TT() {
        binding.cong.setOnClickListener {
            count++
            binding.soluong.text = count.toString()
            soluong = count.toString().toIntOrNull()!!
            Log.d("detail", "onViewCreated:${binding.soluong.text.toString()} ")
            updateTongTienPT()
            tongtienmon = tongtienpt
            Log.d(TAG, "TT: ${tongtienpt}")
        }
        binding.tru.setOnClickListener {
            count--
            if (count < 0) {
                count = 0
                binding.soluong.text = count.toString()
            } else {
                binding.soluong.text = count.toString()
                Log.d("detail", "onViewCreated:${binding.soluong.text.toString()} ")
                updateTongTienPT()
                updateTongTienPT()
                tongtienmon = tongtienpt
                Log.d(TAG, "TT: ${tongtienpt}")
            }
            binding.back.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Homef()).commit()
            }
        }
    }

    private fun Changecolorsize() {
        val buttonList = arrayOf(
            binding.sizel, binding.sizes, binding.sizem
        )
        var selectedButton: Button? = null
        for (button in buttonList) {
            button.setOnClickListener {
                if (selectedButton != null) {
                    selectedButton!!.setBackgroundColor(Color.parseColor("#EDE5E5"))
                }
                button.setBackgroundColor(Color.parseColor("#B0E3EA"))
                selectedButton = button
                Size = button.text.toString()
                Log.d("detail", "Changecolorsize: $Size")
                updateTongTienPT()
                tongtienmon = tongtienpt
            }
        }
    }

    private fun updateTongTienPT() {
        tongtienpt = if (Size == "S") {
            giatien * binding.soluong.text.toString().toInt()
        } else if (Size == "M") {
            val priceForM = giatien * binding.soluong.text.toString().toInt()
            priceForM + priceForM * 10 / 100
        } else {
            giatien * binding.soluong.text.toString()
                .toInt() + giatien * binding.soluong.text.toString().toInt() * 20 / 100
        }
    }

    private fun Showdata() {
        giatien = requireArguments().getInt("giatien")
        tenmon = requireArguments().getString("tenmon")
        calo = requireArguments().getInt("calo")
        img = requireArguments().getString("img")
        mota = requireArguments().getString("mota")
        binding.clas.text = tenmon
        binding.calo.text = "$calo Calories"
        binding.gia.text = "$giatien$"
        binding.tenmon.text = mota
        Picasso.get().load(img).into(binding.imgmonan)
    }
}