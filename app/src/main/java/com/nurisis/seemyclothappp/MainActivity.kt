package com.nurisis.seemyclothappp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nurisis.seemyclothappp.data.repository.SearchRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val searchRepository by inject<SearchRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val result = searchRepository.searchCloth("청바지")
            Log.d("LOG>>","result : $result")
        }

    }
}
