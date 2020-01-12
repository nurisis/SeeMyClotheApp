package com.nurisis.seemyclothappp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.nurisis.seemyclothappp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController:NavController

    private val shopViewModel by viewModel<ShopViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,
            R.id.my_nav_host_fragment
        )
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        intentFromShare()

        observerViewModel()
    }

    private fun observerViewModel() {
        shopViewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        shopViewModel.clickedItem.observe(this, Observer {
            // Clicked from search page
            if(it.title.isNotEmpty())
                navController.navigate(R.id.action_clothesListFragment_to_detailWebViewFragment)
            // Clicked from my cart page
            else
                navController.navigate(R.id.action_cartListFragment_to_detailWebViewFragment)
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun intentFromShare() {
        intent?.let {
            if(Intent.ACTION_SEND == it.action && it.type!=null){
                val imageUri = it.getParcelableExtra(Intent.EXTRA_STREAM) as Uri
                val navAction = CartListFragmentDirections.actionCartListFragmentToBookmarkFromCaptureFragment(imageUri)
                navController.navigate(navAction)
            }
        }
    }

}
