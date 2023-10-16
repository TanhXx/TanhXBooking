package com.example.booking

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.Adapter.SaleOff
import com.example.booking.Api.apiGiamGia
import com.example.booking.Model.FakeData
import com.example.booking.Model.Listitem
import com.example.booking.databinding.FragmentCampaignsfBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class Campaignsf : Fragment() {
    var ds : ArrayList<FakeData> = ArrayList()

    var TAG = "huhu"
    var lastY = 0f
    var checknavi = true
    private lateinit var binding : FragmentCampaignsfBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationPermissionCode = 123
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCampaignsfBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.rcv.adapter = SaleOff(requireContext(),ds)
        binding.rcv.layoutManager = LinearLayoutManager(requireContext())
        callApi()

          binding.rcv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentY = recyclerView.computeVerticalScrollOffset().toFloat()
                Log.d(TAG, "onScrolled: ${currentY}-")
                val delta = currentY - lastY
                val frag1 = requireActivity().supportFragmentManager.findFragmentByTag("f1") as Homef
                if (delta > 0 && checknavi) {
                 /*   binding.navis.animate().translationY(binding.navis.height.toFloat())*/
                    frag1.showhidenavi(checknavi)
                    checknavi = false
                } else if (delta < 0 && !checknavi) {
                   /* binding.navis.animate().translationY(0f)*/
                    frag1.showhidenavi(checknavi)
                    checknavi = true
                }
                lastY = currentY
            }
        })

        // Kiểm tra quyền truy cập vị trí
        if (isLocationPermissionGranted()) {
            // Quyền đã được cấp hoặc đã được yêu cầu
            getLastKnownLocation()
        } else {
            // Yêu cầu quyền truy cập vị trí
            requestLocationPermission()
        }


    }

    private fun callApi() {
        apiGiamGia.api.getData("desc", "activity", "Android", "stackoverflow")
            .enqueue(object : Callback<Listitem> {
                override fun onResponse(call: Call<Listitem>, response: Response<Listitem>) {
                    Log.d("checkrq", "onResponse: ")
                    if(response.isSuccessful){
                        binding.progress.visibility = View.GONE
                        val mlist = response.body()
                        val items = mlist!!.items
                        Log.d(TAG, "onResponse: ${items}")
                      /*  SingletonData.addItems(items!!.map { Listitem(it) })*/
                        SingletonData.addItems(items!!)
                        for (item in items!!){
                            ds.add(item)
                            binding.rcv.adapter!!.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<Listitem>, t: Throwable) {
                    Log.d("checkrq", "onFailure: ")
                }
            })
    }

    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Lấy vị trí cuối cùng của thiết bị
    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    // Xử lý dữ liệu vị trí ở đây
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // ...
                    Log.d("huhu", "${latitude} - ${longitude} ")
                    address = getAddressFromLocation(requireContext(), latitude, longitude)

                }
            }
    }

    fun getAddressFromLocation(context: Context, latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        if (addresses!!.isNotEmpty()) {
            val address = addresses[0]
            val addressLine = address.getAddressLine(0) // Lấy địa chỉ dòng đầu tiên
            return addressLine
        }

        return "Không tìm thấy địa chỉ"
    }

    // Hàm yêu cầu quyền truy cập vị trí
    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            locationPermissionCode
        )
    }

    companion object{
        var address : String = ""
    }

}