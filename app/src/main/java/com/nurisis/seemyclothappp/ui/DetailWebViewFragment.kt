package com.nurisis.seemyclothappp.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.nurisis.seemyclothappp.MainActivity
import com.nurisis.seemyclothappp.databinding.FragmentDetailWebviewBinding
import kotlinx.android.synthetic.main.fragment_detail_webview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailWebViewFragment : Fragment(), OnBackPressedListener{

    private val shopViewModel by sharedViewModel<ShopViewModel>()
    private lateinit var viewDataBinding : FragmentDetailWebviewBinding

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailWebViewFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentDetailWebviewBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailWebViewFragment
            viewModel = shopViewModel
        }

        setUpWebView()

        return viewDataBinding.root
    }

    private fun setUpWebView() {
        viewDataBinding.webView.let {
            it.webViewClient = customWebViewClient
            it.setNetworkAvailable(true) // Informs WebView of the network state
            it.clearCache(true)
            it.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    viewDataBinding.progressBar.progress = newProgress
                }
            }
        }

        // Setting of webview
        viewDataBinding.webView.settings.let {
            it.javaScriptEnabled = true
            it.loadWithOverviewMode = true // Allow a metatag
            it.useWideViewPort = true // Fit a screen size
            it.setSupportZoom(true) // Zoom of screen
            it.builtInZoomControls = true // Zoom in or out of screen
            it.cacheMode = WebSettings.LOAD_NO_CACHE // Cache of browser
        }
    }

    private val customWebViewClient = object : WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            viewDataBinding.progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            viewDataBinding.progressBar.visibility = View.GONE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shopViewModel.clickedItem.value?.let {
            viewDataBinding.webView.loadUrl(it.link)
        }
    }

    override fun onBackPressed() {
        Log.d("LOG>>", "------ onBackPressed --------")
        if(viewDataBinding.webView.canGoBack()) viewDataBinding.webView.goBack()
        else (activity as? MainActivity)?.onBackPressed()
    }
}
