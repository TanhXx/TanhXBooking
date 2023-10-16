package com.example.booking.Model

data class Loginnew(
    val `data`: Data,
    val message: String,
    val status: Boolean
)
data class TkMK(
    val password: String,
    val username: String
)

data class Data(
    val token: String
)


