package com.nurisis.seemyclothappp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nurisis.seemyclothappp.data.NaverShopItem
import com.nurisis.seemyclothappp.databinding.ItemShopListBinding

class ShopListAdapter(private val viewModel: ShopViewModel) : ListAdapter<NaverShopItem, ShopListAdapter.ViewHolder>(
    MyPageDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent,
            viewModel
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemShopListBinding, private val viewModel: ShopViewModel) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: NaverShopItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        /**
         * When click a performance item from performance list,
         * Go to a detail page of item
         * */
        override fun onClick(v: View?) {
            binding.item?.let {
                viewModel.clickItem(it)

                // Page navigation with the navController
//                Navigation.findNavController(binding.root).navigate(R.id.action_frag_list_to_concertDetailFragment, bundle)
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: ShopViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemShopListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
    }
}

class MyPageDiffCallback : DiffUtil.ItemCallback<NaverShopItem>() {
    override fun areItemsTheSame(oldItem: NaverShopItem, newItem: NaverShopItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NaverShopItem, newItem: NaverShopItem): Boolean {
        return oldItem == newItem
    }
}