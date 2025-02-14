package ru.netology.nmedia.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.netology.nmedia.api.ApiService
import ru.netology.nmedia.dto.Post

class PostPagingSource (
    private val apiService: ApiService
) : PagingSource<Long, Post>() {
    override fun getRefreshKey(state: PagingState<Long, Post>): Long? = null

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Post> {
        when (params) {
            is LoadParams.Refresh -> {
                apiService.getLatest(params.loadSize)
            }
            //скролл вниз
            is LoadParams.Append ->
                //скролл вверх
            is LoadParams.Prepend -> LoadResult.Page(
                data = emptyList(), nextKey = null, prevKey = params.key,
            )

        }
    }
}