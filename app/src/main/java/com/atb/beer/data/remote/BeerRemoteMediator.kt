package com.atb.beer.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.atb.beer.data.local.BeerDatabase
import com.atb.beer.data.local.entity.BeerEntity
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val db: BeerDatabase,
    private val api: BeerApi
): RemoteMediator<Int, BeerEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                REFRESH -> 1
                PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1
                    else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val beers = api.getBeers(loadKey, state.config.pageSize)
            db.withTransaction {
                if (loadType == REFRESH) db.dao.deleteAll()
                val beersEntity = beers.map { it.toBeerEntity() }
                db.dao.upsert(beersEntity)
            }
            MediatorResult.Success(endOfPaginationReached = beers.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}