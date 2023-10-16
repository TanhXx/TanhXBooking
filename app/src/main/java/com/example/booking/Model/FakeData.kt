package com.example.booking.Model

data class FakeData(
    val answer_count: Int,
    val content_license: String,
    val creation_date: Int,
    val is_answered: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: Owner,
    val question_id: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val view_count: Int
)

data class Owner(
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String
)