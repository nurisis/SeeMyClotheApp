package com.nurisis.seemyclothappp

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nurisis.seemyclothappp.data.NaverSearchResult
import com.nurisis.seemyclothappp.data.NaverShopItem
import com.nurisis.seemyclothappp.data.Result
import com.nurisis.seemyclothappp.domain.CartUseCase
import com.nurisis.seemyclothappp.domain.SearchClothUseCase
import com.nurisis.seemyclothappp.entity.State
import com.nurisis.seemyclothappp.ui.ShopViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.lang.Exception

class ShopViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private val searchClothUseCase = mock(SearchClothUseCase::class.java)
    @Mock
    private val addToCartUseCase = mock(CartUseCase::class.java)

    private val viewModel :ShopViewModel by lazy { ShopViewModel(searchClothUseCase, addToCartUseCase) }

    @Mock
    lateinit var searchObserver: Observer<List<NaverShopItem>>

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun whenBeforeSearch_State_ShouldBeSTART(){
        runBlocking {
            verify(searchClothUseCase, never()).search("")
            assertTrue(viewModel.searchState.value == State.START)
        }
    }

    @Test
    fun whenSearchWithResult_State_ShouldBeSHOW(){
        runBlocking {
            val query = "jeans"
            `when`(searchClothUseCase.search(ArgumentMatchers.anyString())).thenReturn(
                Result.Success(NaverSearchResult(
                    total = 10,
                    items = listOf(NaverShopItem(title = "Test", link = "www", image = "www.", mallName = "", productId = 0, lprice = 0, hprice = 0)),
                    display = 10,
                    start = 1
                ))
            )

            viewModel.search(query)

            viewModel.searchList.observeForever(searchObserver)

            verify(searchObserver, atLeastOnce()).onChanged(ArgumentMatchers.anyList())
            verify(searchClothUseCase, atLeastOnce()).search(query)
            assertTrue(viewModel.searchState.value == State.SHOW)
        }
    }

    @Test
    fun whenSearchWithOutResult_State_ShouldBeNONE(){
        runBlocking {
            val query = "xxx"
            `when`(searchClothUseCase.search(ArgumentMatchers.anyString())).thenReturn(
                Result.Error(Exception(""))
            )

            viewModel.search(query)
            viewModel.searchList.observeForever(searchObserver)

            verify(searchClothUseCase, atLeastOnce()).search(query)
            verify(searchObserver, never()).onChanged(ArgumentMatchers.anyList())
            assertTrue(viewModel.searchState.value == State.NONE)
        }
    }

    @Test
    fun whenSearchWithEmtpyString_ShouldReturnNothing(){
        runBlocking {
            val query = ""

            viewModel.search(query)

            verify(searchObserver, never()).onChanged(ArgumentMatchers.anyList())
            verify(searchClothUseCase, never()).search(query)
        }
    }


    @Test
    fun addToCartFromWebView_onlyWork_whenUrlIsNotEmpty() {
        runBlocking {
            `when`(addToCartUseCase.addCartFromWeb(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Unit)

            val urlTest = "www"
            val titleTest = "Naver shopping"
            val imageUrlTest = "www.dfsd"

            viewModel.addToCartFromWebView(urlTest, titleTest, imageUrlTest)

            verify(addToCartUseCase, atLeastOnce()).addCartFromWeb(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        }
    }

}