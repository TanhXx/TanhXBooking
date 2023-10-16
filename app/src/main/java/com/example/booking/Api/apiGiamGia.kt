package com.example.booking.Api
import com.example.booking.Model.Listitem
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
interface apiGiamGia {
companion object{
    var retrofit = Retrofit.Builder().baseUrl("https://api.stackexchange.com/2.2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var api = retrofit.create(apiGiamGia::class.java)
}
    @GET("search")
    fun getData(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("tagged") tagged: String,
        @Query("site") site: String
    ): Call<Listitem>

}