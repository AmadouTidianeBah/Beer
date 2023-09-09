package com.atb.beer.domain

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val firstBrewed: String,
    val contributedBy: String
)
