package com.nurisis.seemyclothappp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.nurisis.seemyclothappp.ui.DetailWebViewFragment
import com.nurisis.seemyclothappp.ui.OnBackPressedListener
import com.nurisis.seemyclothappp.ui.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController:NavController

    private val shopViewModel by viewModel<ShopViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        observerViewModel()
    }

    private fun observerViewModel() {
        shopViewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        shopViewModel.clickedItem.observe(this, Observer {
            navController.navigate(R.id.action_clothesListFragment_to_detailWebViewFragment)
        })
    }

    // TODO :: 이거뭐야?
    override fun onSupportNavigateUp(): Boolean {
        Log.d("LOG>>", "------ onSupportNavigateUp() --------")
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        Log.d("LOG>>", "------ onBackPressed() in MainActivity --------")
        super.onBackPressed()
    }
}
