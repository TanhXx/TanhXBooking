package com.example.booking.Api

import com.example.booking.Model.Login
import com.example.booking.Model.Register
import com.example.booking.Model.RegisterX
import com.example.booking.Model.TkMK
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface Apilogin {
    companion object{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://dd5d-2405-4803-fe74-70c0-b4eb-868c-1595-654a.ngrok-free.app/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var apilogin = retrofit.create(Apilogin::class.java)

    }
    @POST("login")
    fun getData(@Body tkMK: TkMK): Call<Login>

    @POST("registration")
    fun Register(@Body register: Register): Call<RegisterX>
}