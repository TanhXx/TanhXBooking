package com.example.booking

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.booking.R
import com.example.booking.databinding.FragmentDeliiciousFoodfBinding

class DeliiciousFoodf : Fragment() {
    lateinit var binding: FragmentDeliiciousFoodfBinding
    private var isTextVisible = true
    var textIndex = 0
    var count = 0
    val text = listOf<String>("Giao nhanh", "Đầu bếp chuẩn 5 sao")
    val text2 = listOf("Vận chuyển nhanh - Giá siêu tốt","Chuyên nghiệp - Sáng tạo - Tận tâm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliiciousFoodfBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnnextdeli.setOnClickListener {
            animflash()
            count ++
            if (count ==3 ){

                val bts = Loginb()
                bts.show(parentFragmentManager,bts.tag)

            }
        }


        return view
    }

    private fun animflash() {
        val animationDuration = 1000L
        val oldValue = if (isTextVisible) 0f else -1.2f*binding.tv1.width.toFloat()
        val newValue = if (isTextVisible) -1.2f*binding.tv1.width.toFloat() else 0f
        val oldValue1 = if (isTextVisible) 0f else -1.2f*binding.tv2.width.toFloat()

        val newValue1 = if (isTextVisible) -1.2f*binding.tv2.width.toFloat() else 0f

        val animator = ValueAnimator.ofFloat(oldValue, newValue)
        animator.duration = animationDuration
        val animator1 = ValueAnimator.ofFloat(oldValue1, newValue1)
        animator1.duration = animationDuration

        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            binding.tv1.translationX = value
        }
        animator1.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            binding.tv2.translationX = value
        }

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                textIndex = (textIndex + 1) % text.size
                if(textIndex == 1){
                    binding.line.setImageResource(R.drawable.sllider_element__1_)
                }else
                    binding.line.setImageResource(R.drawable.sllider_element__2_)

                binding.tv1.text = text[textIndex]
                binding.tv1.translationX = 0f // Đặt lại giá trị translationX
            }
            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
        animator1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                binding.tv2.text = text2[textIndex]
                binding.tv2.translationX = 0f // Đặt lại giá trị translationX
            }
            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
        animator.start()
        animator1.start()
    }
}
