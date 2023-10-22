package com.example.booking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.Adapter.GiohangAdapter
import com.example.booking.Model.CartViewModel
import com.example.booking.Model.Giohang
import com.example.booking.databinding.FragmentCartBinding


class Cart : Fragment() {
    lateinit var binding: FragmentCartBinding
    var ds : ArrayList<Giohang> = ArrayList()
    var sum = 0
    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcv.adapter = GiohangAdapter(this, cartViewModel.cartItems,cartViewModel)
        binding.rcv.layoutManager = LinearLayoutManager(requireContext())
        binding.tv3.text = Campaignsf.address
        for (item in cartViewModel.cartItems){
            ds.add(item)
        }
        updateTotalPrice()
        binding.tongtien.text = sum.toString()

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.maincontainer,Homef()).commit()
        }
    }
    fun updateTotalPrice() {
        sum = 0
        for (item in cartViewModel.cartItems) {
            sum += item.tongtien
        }
        binding.tongtien.text = sum.toString()
    }
}
