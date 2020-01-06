package com.nurisis.seemyclothappp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nurisis.seemyclothappp.R
import com.nurisis.seemyclothappp.databinding.FragmentBookmarkFromCaptureBinding
import com.nurisis.seemyclothappp.databinding.FragmentClothesListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BookmarkFromCaptureFragment : Fragment() {

    private val shopViewModel by sharedViewModel<ShopViewModel>()
    private lateinit var viewDataBinding : FragmentBookmarkFromCaptureBinding

    val args: BookmarkFromCaptureFragmentArgs by navArgs()

    private lateinit var imageUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentBookmarkFromCaptureBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@BookmarkFromCaptureFragment
            viewModel = shopViewModel
        }

        viewDataBinding.buttonBookmark.setOnClickListener {
            shopViewModel.addToCartFromShare(imageUri, viewDataBinding.etTitle.text.toString())
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageUri = args.imageUri

//        Log.d("LOG>>", "uri : ${imageUri.toString()}")
//        Log.d("LOG>>", "uri path : ${imageUri.path}")
//        Log.d("LOG>>", "uri scheme : ${imageUri.scheme}")
//        Log.d("LOG>>", "uri encodedAuthority : ${imageUri.encodedAuthority}")
//        Log.d("LOG>>", "uri encodedSchemeSpecificPart : ${imageUri.encodedSchemeSpecificPart}")
//        Log.d("LOG>>", "uri normalizeScheme : ${imageUri.normalizeScheme()}")

        viewDataBinding.ivCaptured.setImageURI(imageUri)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shopViewModel.sharedToBookmarkDone.observe(viewLifecycleOwner, Observer {
            if(it) findNavController().navigate(R.id.action_bookmarkFromCaptureFragment_to_cartListFragment)
        })
    }
}
