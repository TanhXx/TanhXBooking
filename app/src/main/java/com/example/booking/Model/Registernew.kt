package com.example.booking.Model

data class Registernew(
    val `data`: DataXX,
    val message: String,
    val status: Boolean
)
data class DataXX(
    val email: List<String>,
    val username: List<String>
)

data class Register(
    val email: String,
    val password: String,
    val phone_number: String,
    val username: String
)