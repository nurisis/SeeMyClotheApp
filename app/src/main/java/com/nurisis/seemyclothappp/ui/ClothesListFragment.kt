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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

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
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        viewDataBinding.etQuery.setOnEditorActionListener { v, actionId, event ->
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClothesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ClothesListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
