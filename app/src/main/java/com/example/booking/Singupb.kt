package com.example.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.booking.Api.ApiRegis
import com.example.booking.Api.Apilogin
import com.example.booking.Model.Register
import com.example.booking.Model.RegisterX
import com.example.booking.databinding.BsingupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Singupb : BottomSheetDialogFragment() {
    lateinit var binding : BsingupBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BsingupBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
          Hidekeyboard.hidekey(requireContext(),view)
        }
        isCancelable = false
        binding.login.setOnClickListener {
            val login = Loginb()
            dismiss()
            login.show(parentFragmentManager,login.tag)
        }

        binding.dk.setOnClickListener {
            var tk = binding.tk.text.toString()
            var email = binding.email.text.toString()
            var sdt = binding.sdt.text.toString()
            var mk = binding.mk.text.toString()

            var regis = Register(email, mk, sdt, tk)
            Apilogin.apilogin.Register(regis).enqueue(object : Callback<RegisterX> {
                override fun onResponse(call: Call<RegisterX>, response: Response<RegisterX>) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), "Đăng kí thành công", Toast.LENGTH_SHORT).show()
                        dismiss()
                        parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Loginb(),"f2").commit()
                    } else {
                        Toast.makeText(requireContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show()
                        Log.d("checkapi", "onResponse: ${response}")
                    }
                }

                override fun onFailure(call: Call<RegisterX>, t: Throwable) {
                    Toast.makeText(requireContext(), "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show()
                }
            })
        }



    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialogTheme
    }




}