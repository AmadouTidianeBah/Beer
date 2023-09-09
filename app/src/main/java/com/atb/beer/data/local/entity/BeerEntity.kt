package com.atb.beer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atb.beer.domain.Beer

@Entity
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val imageUrl: String?,
    val firstBrewed: String,
    val contributedBy: String
) {
    fun toBeer(): Beer {
        return Beer(id, name, tagline, description, imageUrl, firstBrewed, contributedBy)
    }
}
