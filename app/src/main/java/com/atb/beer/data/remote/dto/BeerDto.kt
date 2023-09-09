package com.atb.beer.data.remote.dto

import com.atb.beer.data.local.entity.BeerEntity

data class BeerDto(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val image_url: String?,
    val first_brewed: String,
    val contributed_by: String
) {
    fun toBeerEntity(): BeerEntity {
        return BeerEntity(id, name, tagline, description, image_url, first_brewed, contributed_by)
    }
}