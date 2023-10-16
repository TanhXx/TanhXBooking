package com.example.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.booking.databinding.SplashBinding


class Splash : AppCompatActivity() {
    lateinit var binding : SplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                supportFragmentManager.beginTransaction().replace(R.id.maincontainer, DeliiciousFoodf()).commit()
            }
        }.start()

    }
}