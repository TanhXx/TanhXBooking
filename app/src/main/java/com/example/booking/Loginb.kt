package com.example.booking

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.example.booking.Api.Apilogin
import com.example.booking.Model.Listproduct
import com.example.booking.Model.Loginnew
import com.example.booking.Model.Singleton
import com.example.booking.Model.TkMK
import com.example.booking.databinding.BloginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Loginb : BottomSheetDialogFragment() {
    lateinit var inputtext : InputMethodManager
    var checkmk = false
    var TAG = "checkapi"
    private lateinit var binding: BloginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BloginBinding.inflate(inflater,container,false)
        inputtext = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
           Hidekeyboard.hidekey(requireContext(),view)
        }

        Loginup()
        Checkmk()
        binding.login.setOnClickListener {
            callapi()

        }
    }

    private fun callapi() {
          val user = TkMK( binding.mk.text.toString(),binding.tk.text.toString())
          Apilogin.apilogin.Login(user).enqueue(object : Callback<Loginnew>{
              override fun onResponse(call: Call<Loginnew>, response: Response<Loginnew>) {
                  if (response.isSuccessful){
                    val login = response.body()
                       token = login!!.data.token
                      if(response.code() == 200){
                          parentFragmentManager.beginTransaction().replace(R.id.maincontainer, Homef(),"f1").commit()
                          dismiss()
                      }
                  }else {
                      binding.edtMk.error = "Tài khoản hoặc mật khẩu không chính xác"
                      val redColor = ContextCompat.getColor(requireContext(), R.color.red)
                      binding.edtMk.setEndIconDrawable(R.drawable.eyered)
                      binding.mk.setBackgroundResource(R.drawable.custom_checkmkfailse)
                      binding.mk.setTextColor(redColor)
                      binding.edtMk.endIconDrawable!!.setColorFilter(redColor, PorterDuff.Mode.SRC_IN)
                      binding.mk.setOnClickListener {
                          binding.tk.requestFocus()
                          ErroText()
                      }
                      binding.tk.setOnClickListener {
                          binding.tk.requestFocus()
                          ErroText()
                      }
                  }


              }
              override fun onFailure(call: Call<Loginnew>, t: Throwable) {
                  Log.d(TAG, "onFailure: " )
              }

          })
      }
/*      private fun getall(beartoken : String){
        Apilogin.apilogin.getProducts(beartoken).enqueue(object : Callback<Listproduct>{
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<Listproduct>, response: Response<Listproduct>) {
                if(response.isSuccessful){
                   var mlist = response.body()
                    var list = mlist!!.data.products
                        Singleton.addItems(list)
                }
            }

            override fun onFailure(call: Call<Listproduct>, t: Throwable) {
                Log.d("getall", "onFailure: ")
            }
        })
    }*/

    @SuppressLint("ResourceAsColor")


    private fun Checkmk() {
        binding.mk.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))
        binding.edtMk.setEndIconOnClickListener {
            checkmk = !checkmk
            if (checkmk){
                binding.mk.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD
            }
            else{
                binding.mk.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                        android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
    }

    private fun ErroText(){
        binding.edtMk.error = null
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.black)
        binding.edtMk.setEndIconDrawable(R.drawable.eye)
        binding.mk.setBackgroundResource(R.drawable.custom_edtlog)
        binding.mk.setTextColor(defaultColor)
        binding.edtMk.endIconDrawable!!.setColorFilter(defaultColor, PorterDuff.Mode.SRC_IN)
    }



    private fun Loginup() {
        isCancelable = false
        binding.singup.setOnClickListener {
            var singup = Singupb()
            dismiss()
            singup.show(parentFragmentManager,singup.tag)
        }

        binding.forgot.setOnClickListener {
            var fotgot = Bforgot()
            dismiss()
            fotgot.show(parentFragmentManager,fotgot.tag)
        }
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialogTheme
    }

    companion object{
        var token : String? = null
    }


}