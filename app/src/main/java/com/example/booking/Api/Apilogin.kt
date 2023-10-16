package com.example.booking.Api

import com.example.booking.Model.Listproduct
import com.example.booking.Model.Loginnew
import com.example.booking.Model.Register
import com.example.booking.Model.Registernew
import com.example.booking.Model.TkMK
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Apilogin {
    companion object{
        var retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-13-231-122-229.ap-northeast-1.compute.amazonaws.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var apilogin = retrofit.create(Apilogin::class.java)


    }
    @POST("login")
    fun Login(@Body tkMK: TkMK): Call<Loginnew>

    @POST("registration")
    fun Register(@Body register: Register): Call<Registernew>

    @GET("product/get-all")
    fun getProducts(@Header("Authorization") authorization: String): Call<Listproduct>
}