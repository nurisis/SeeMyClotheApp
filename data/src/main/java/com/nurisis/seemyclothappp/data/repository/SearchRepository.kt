package com.nurisis.seemyclothappp.data.repository

import android.util.Log
import com.nurisis.seemyclothappp.data.NaverSearchApi
import com.nurisis.seemyclothappp.data.NaverSearchResult
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.data.base.BaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SearchRepository (
    private val apiSource: NaverSearchApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository() {

    suspend fun searchCloth(query:String, sort:String="sim", start:Int = 1) : Result<NaverSearchResult> = withContext(ioDispatcher) {
        return@withContext getResult { apiSource.searchCloth(query, sort= sort, start = start)  }
    }
}
