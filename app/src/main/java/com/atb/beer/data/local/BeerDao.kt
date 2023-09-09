package com.atb.beer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.atb.beer.data.local.entity.BeerEntity

@Dao
interface BeerDao {
    @Upsert
    suspend fun upsert(beers: List<BeerEntity>)

    @Query("DELETE FROM beerentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM beerentity")
    fun getBeers(): PagingSource<Int, BeerEntity>
}