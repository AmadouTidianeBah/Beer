package com.atb.beer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atb.beer.data.local.entity.BeerEntity

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
abstract class BeerDatabase: RoomDatabase() {
    abstract val dao: BeerDao

    companion object {
        const val NAME = "beer_db"
    }
}