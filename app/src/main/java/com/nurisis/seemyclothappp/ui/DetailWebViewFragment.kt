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
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nurisis.seemyclothappp.MainActivity
import com.nurisis.seemyclothappp.databinding.FragmentDetailWebviewBinding
import kotlinx.android.synthetic.main.fragment_detail_webview.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements
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

        viewDataBinding.ivAddCart.setOnClickListener {
            getHtmlContent(url = viewDataBinding.webView.url)
            Toast.makeText(activity, "Url : ${viewDataBinding.webView.url}", Toast.LENGTH_SHORT).show()
        }

        return viewDataBinding.root
    }

    private fun getHtmlContent(url : String) {
        GlobalScope.launch {
            val document = Jsoup.connect(url).get()
            filterOgtag(document.select("meta[property^=og:]"))
        }
    }

    private fun filterOgtag(ogTags:Elements) {
        ogTags.forEach {
            when(it.attr("property")) {
                "og:title" -> Log.d("LOG>>","Title : ${it.attr("content")}")
                "og:image" -> Log.d("LOG>>","Image : ${it.attr("content")}")
            }
        }
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
