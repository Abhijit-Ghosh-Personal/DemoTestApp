package com.demotestapplication.model

data class AppListResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean
)