package com.example.booking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.Adapter.getAllAdapter
import com.example.booking.Model.ListLove
import com.example.booking.Model.Singleton
import com.example.booking.Model.getAll
import com.example.booking.databinding.FragmentSearchfBinding

class Love : Fragment(){
   private lateinit var binding : FragmentSearchfBinding
   var ds : ArrayList<getAll> = ArrayList()
    var TAG = "Searchf"
    var addDs = mutableListOf<getAll>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchfBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("check", "onViewCreated: ")
        binding.rcvlove.layoutManager = LinearLayoutManager(requireContext())
        var adapter = getAllAdapter(requireContext(),ds)
        adapter.setClickable(false)
        binding.rcvlove.adapter = adapter

    }
    override fun onResume() {
        super.onResume()

        var addDs = mutableListOf<getAll>()
        var remoteds = mutableListOf<getAll>()
        for (item in Singleton.getItems()) {
            if (item.check && !ds.contains(item)) {
                Log.d(TAG, "onResume: k trùng")
                addDs.add(item)
            } else if (!item.check && ds.contains(item)) {
                Log.d(TAG, "onResume: Trùng ds")
                remoteds.add(item)
            }
        }
        ds.addAll(addDs)
        Log.d(TAG, "onResume: ${addDs}")
        ds.removeAll(remoteds)
        Log.d(TAG, "onResume: ${ds.size}")

        (binding.rcvlove.adapter as getAllAdapter).notifyDataSetChanged()
        if (addDs.size > 0) {
            binding.pro.visibility = View.GONE
            Log.d(TAG, "onResume: ${addDs.size}")
        } else if (addDs.size <= 0) {
            binding.pro.visibility = View.VISIBLE
            Log.d(TAG, "onResume: ${addDs.size}")
        }


    }



}