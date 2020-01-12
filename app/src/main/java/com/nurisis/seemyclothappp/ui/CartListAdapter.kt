package com.nurisis.seemyclothappp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nurisis.seemyclothappp.data.local.Cart
import com.nurisis.seemyclothappp.databinding.ItemCartListBinding

class CartListAdapter(private val viewModel: ShopViewModel) : ListAdapter<Cart, CartListAdapter.ViewHolder>(
    CartDiffCallback()
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

    class ViewHolder private constructor(val binding: ItemCartListBinding, private val viewModel: ShopViewModel) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: Cart) {
            binding.item = item
            binding.executePendingBindings()
        }

        /**
         * When click a item from user's cart list,
         * Go to a detail page of item
         * */
        override fun onClick(v: View?) {
            binding.item?.let {
                viewModel.clickCartItem(it)
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: ShopViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCartListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
    }
}

class CartDiffCallback : DiffUtil.ItemCallback<Cart>() {
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.cart_id == newItem.cart_id
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem == newItem
    }
}