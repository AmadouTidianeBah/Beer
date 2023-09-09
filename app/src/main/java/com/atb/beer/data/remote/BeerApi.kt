package com.atb.beer.data.remote

import com.atb.beer.data.remote.dto.BeerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    @GET("beers")
    suspend fun getBeers(
        @Query("page") pager: Int,
        @Query("per_page") perPage: Int
    ): List<BeerDto>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}