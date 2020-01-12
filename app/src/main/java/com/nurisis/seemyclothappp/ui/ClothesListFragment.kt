package com.nurisis.seemyclothappp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nurisis.seemyclothappp.databinding.FragmentClothesListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ClothesListFragment : Fragment() {

    private val shopViewModel by sharedViewModel<ShopViewModel>()
    private lateinit var listAdapter: ShopListAdapter
    private lateinit var viewDataBinding : FragmentClothesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentClothesListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ClothesListFragment
            viewModel = shopViewModel
        }

        listAdapter = ShopListAdapter(shopViewModel)
        viewDataBinding.rvSearch.apply {
            adapter = listAdapter
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,1)
            layoutManager = staggeredGridLayoutManager
            itemAnimator = null
        }

        viewDataBinding.etQuery.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH)
                shopViewModel.search(viewDataBinding.etQuery.text.toString())

            return@setOnEditorActionListener true
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shopViewModel.searchList.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
}
