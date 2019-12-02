package com.nurisis.seemyclothappp

import org.junit.Assert.*
import org.junit.Test

class ShopViewModelTest {

    private val viewModel = ShopViewModel()

    @Test
    fun searchWithEmptyQuery() {
        viewModel.search("")
        assertNull(viewModel.searchList)
    }

    @Test
    fun searchWithNotEmptyQuery() {
        viewModel.search("Jeans")
        assertNotNull(viewModel.searchList)
    }
}