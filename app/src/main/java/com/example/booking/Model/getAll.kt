package com.example.booking.Model

data class getAll(
    val calories: Int,
    val category: String,
    val created_at: Any,
    val description: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: Int,
    val size: String,
    val updated_at: Any
)

data class DataX(
    val products: List<getAll>
)

class Listproduct(
    val data: DataX
)
