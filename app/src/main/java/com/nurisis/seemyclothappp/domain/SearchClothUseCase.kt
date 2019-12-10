package com.nurisis.seemyclothappp.domain

import com.nurisis.seemyclothappp.data.NaverSearchResult
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.data.repository.SearchRepository
import java.lang.Exception

class SearchClothUseCase (
    private val searchRepository: SearchRepository
) {

    suspend fun search(query:String) : Result<NaverSearchResult> {
        val result = searchRepository.searchCloth(query)

        if(result is Result.Success) {
            if(result.data.items.isEmpty()) return Result.Error(Exception("No result"))
        }

        return result
    }
}