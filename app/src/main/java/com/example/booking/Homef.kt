package com.example.booking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.booking.Adapter.ViewpagerAdapter
import com.example.booking.Model.CartViewModel
import com.example.booking.databinding.FragmentHomefBinding


class Homef : Fragment() {
    lateinit var binding : FragmentHomefBinding
    var TAG = "huhu"
    var tongtien = 0
  /*   var cartViewModel: CartViewModel? = null*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomefBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Bottomnavi(view)

        binding.viewpager2.adapter = ViewpagerAdapter(this)
        binding.viewpager2.setCurrentItem(2,false)

        Log.d(TAG, "onViewCreated: ${binding.viewpager2.currentItem}")
        showviewpager()

        binding.menuBack.setOnClickListener {
            Toast.makeText(requireContext(), "OK", Toast.LENGTH_SHORT).show()
        }
        binding.gh.setOnClickListener {
            /*if(cartViewModel.cartItems != null){
                for (item in cartViewModel.cartItems){
                    tongtien += item.thanhtien
                }
                var bundle = Bundle()
                bundle.putInt("thanhtoan", tongtien)
                Cart().arguments = bundle
            }*/

            parentFragmentManager.beginTransaction().replace(R.id.maincontainer,Cart()).addToBackStack(null).commit()
        }

        var thanhtien = arguments?.getString("thanhtien")
        Log.d("thanhtien", "onViewCreated: ${thanhtien} ")

    }



    private fun showviewpager() {
        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> {
                        binding.gh.visibility = View.GONE
                        binding.navis.show(1)
                        checkappbar()
                    }
                    1 -> {
                        binding.navis.show(2)
                        checkappbar()
                        binding.gh.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.navis.show(3)
                        checkappbar()
                        binding.gh.visibility = View.VISIBLE
                    }
                    3 ->{
                        binding.navis.show(4)
                        checkappbar()
                        binding.gh.visibility = View.VISIBLE
                    }
                    4 -> {
                        binding.navis.show(5)
                        binding.appbar.visibility = View.GONE
                        binding.tv1.visibility = View.GONE
                        binding.gh.visibility = View.GONE
                    }
                }
            }
        })
    }

    fun checkappbar(){
        if (binding.viewpager2.currentItem == 0 || binding.viewpager2.currentItem == 4){
            Log.d(TAG, "checkappbar: ${binding.viewpager2.currentItem}")
            binding.appbar.visibility = View.GONE
            binding.tv1.visibility = View.GONE
        }else{
            binding.appbar.visibility = View.VISIBLE
            Log.d(TAG, "checkappbar: ${binding.viewpager2.currentItem}")
            binding.tv1.visibility = View.VISIBLE
        }

    }
    private fun Bottomnavi(view :View) {
        val bottomnavi = view.findViewById<MeowBottomNavigation>(R.id.navis)
        bottomnavi.add(MeowBottomNavigation.Model(1,R.drawable.notification))
        bottomnavi.add(MeowBottomNavigation.Model(2,R.drawable.his))
        bottomnavi.add(MeowBottomNavigation.Model(3,R.drawable.home))
        bottomnavi.add(MeowBottomNavigation.Model(4,R.drawable.search))
        bottomnavi.add(MeowBottomNavigation.Model(5,R.drawable.logo))
        bottomnavi.show(3)
        bottomnavi.setOnClickMenuListener { it->
            when(it.id){
                1 -> {binding.viewpager2.currentItem = 0 }
                2 -> { binding.viewpager2.currentItem = 1 }
                3 -> { binding.viewpager2.currentItem = 2 }
                4 -> { binding.viewpager2.currentItem = 3 }
                5 -> { binding.viewpager2.currentItem = 4 }
            }
        }

    }

    fun showhidenavi(check : Boolean){
        if(check){
            binding.navis.animate().translationY(binding.navis.height.toFloat())
        }
        else{
            binding.navis.animate().translationY(0f)
        }
    }





}