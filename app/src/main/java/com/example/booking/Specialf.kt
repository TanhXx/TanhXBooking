package com.example.booking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.booking.Adapter.SpecialAdapter
import com.example.booking.Model.Singleton
import com.example.booking.Model.getAll
import com.example.booking.databinding.FragmentHomefBinding
import com.example.booking.databinding.FragmentSpecialBinding


class Specialf : Fragment() {
    var ds : ArrayList<getAll> = ArrayList()
    private lateinit var binding : FragmentSpecialBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpecialBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewpager = activity?.findViewById<ViewPager2>(R.id.viewpager2)
        binding.rcvspecial.adapter = SpecialAdapter(requireContext(),ds)
        binding.rcvspecial.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        Data()
        binding.vitri.text = Campaignsf.address
        binding.rcvspecial.setOnTouchListener { view, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_DOWN ->{
                    viewpager!!.isUserInputEnabled = false
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->{
                    viewpager!!.isUserInputEnabled = true
                }
            }
            false
        }

    }
    private fun Data() {
        val items = Singleton.getItems()
        Log.d("get", "onViewCreated: ${items}")
        for (item in items){
            ds.add(item)
            binding.rcvspecial.adapter!!.notifyDataSetChanged()
        }
    }


}