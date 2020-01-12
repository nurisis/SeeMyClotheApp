package com.nurisis.seemyclothappp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nurisis.seemyclothappp.R
import com.nurisis.seemyclothappp.databinding.FragmentCartListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CartListFragment : Fragment() {

    private val shopViewModel by sharedViewModel<ShopViewModel>()
    private lateinit var viewDataBinding : FragmentCartListBinding
    private lateinit var listAdapter:CartListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentCartListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CartListFragment
        }

        listAdapter = CartListAdapter(shopViewModel)
        viewDataBinding.rvCarts.apply {
            adapter = listAdapter
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,1)
            layoutManager = staggeredGridLayoutManager
            itemAnimator = null
        }

        viewDataBinding.tvSearchCta.setOnClickListener {
            Navigation.findNavController(viewDataBinding.root).navigate(R.id.action_cartListFragment_to_clothesListFragment)
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shopViewModel.cartList.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }

}
