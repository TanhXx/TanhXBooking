package com.example.booking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.Adapter.SpecialAdapter
import com.example.booking.Model.FakeData
import com.example.booking.Model.Singleton
import com.example.booking.Model.getAll
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rcvspecial.adapter = SpecialAdapter(requireContext(),ds)
        binding.rcvspecial.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
       Data()
        binding.vitri.text = Campaignsf.address

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