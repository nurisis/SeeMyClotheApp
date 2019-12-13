package com.nurisis.seemyclothappp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.nurisis.seemyclothappp.databinding.FragmentBookmarkFromCaptureBinding
import com.nurisis.seemyclothappp.databinding.FragmentClothesListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BookmarkFromCaptureFragment : Fragment() {

    private val shopViewModel by sharedViewModel<ShopViewModel>()
    private lateinit var viewDataBinding : FragmentBookmarkFromCaptureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentBookmarkFromCaptureBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@BookmarkFromCaptureFragment
            viewModel = shopViewModel
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
