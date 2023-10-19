package com.example.booking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
    var TAG = "cart"
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

        binding.rcv.adapter = GiohangAdapter(requireContext(), cartViewModel.cartItems)
        binding.rcv.layoutManager = LinearLayoutManager(requireContext())

        val tenmon = arguments?.getString("tenmon")
        val soluong = arguments?.getString("soluong")
        val gia = arguments?.getString("gia")!!.replace("$", "")
        val img = arguments?.getString("img")

        Log.d(TAG, "onViewCreated: $tenmon - $soluong - $gia - ${img}")


        val gioHangItem = Giohang("${tenmon}", gia!!.toIntOrNull()!!, soluong!!.toIntOrNull()!!, "${img}")
        cartViewModel.addCartItem(gioHangItem)
        cartViewModel.removeDuplicateTenmon()
        binding.rcv.adapter?.notifyDataSetChanged()


        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.maincontainer,Homef()).commit()
        }
    }
}
