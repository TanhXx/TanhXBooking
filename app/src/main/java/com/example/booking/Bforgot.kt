package com.example.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booking.databinding.BforgotBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Bforgot : BottomSheetDialogFragment() {
    lateinit var binding : BforgotBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BforgotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Hidekeyboard.hidekey(requireContext(),view)
        isCancelable = false

        binding.back.setOnClickListener {
            val login = Loginb()
            dismiss()
            login.show(parentFragmentManager,login.tag)
        }


    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialogTheme
    }
}