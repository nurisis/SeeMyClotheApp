package com.nurisis.seemyclothappp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nurisis.seemyclothappp.data.NaverShopItem
import com.nurisis.seemyclothappp.domain.SearchClothUseCase
import com.nurisis.seemyclothappp.entity.State
import com.nurisis.seemyclothappp.ui.ShopViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ShopViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val searchClothUseCase = mock(SearchClothUseCase::class.java)

    private lateinit var viewModel :ShopViewModel

    @Mock
    lateinit var searchObserver: Observer<List<NaverShopItem>>

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        viewModel = ShopViewModel(searchClothUseCase)
    }

    @Test
    fun whenBeforeSearch_State_ShouldBeSTART(){
        runBlocking {
            verify(searchClothUseCase, never()).search("")
            assertTrue(viewModel.searchState.value == State.START)
        }
    }

    @Test
    fun whenSearhListIsNotEmpty_State_ShouldBeSHOW() {

    }

    @Test
    fun whenSearhListIsEmpty_State_ShouldBeNONE(){

    }

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