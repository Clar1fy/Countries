package com.timplifier.countries.data.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.timplifier.karsyhkyrremastered.data.utils.DataMapper
import retrofit2.HttpException
import java.io.IOException

private const val BASE_STARTING_PAGE_INDEX = 1

abstract class BasePagingSource<ValueDto : DataMapper<Value>, Value : Any>(
    private val request: suspend (position: Int) -> BasePagingResponse<ValueDto>,
) : PagingSource<Int, Value>() {

    private var nextOffset: Int? = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val position = params.key ?: BASE_STARTING_PAGE_INDEX
        return try {
            val response = request(position)
            nextOffset = when {
                !response.next.isNullOrEmpty() -> nextOffset?.plus(50)
                response.results.isEmpty() -> null
                else -> null
            }

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = null,
                nextKey = nextOffset
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}