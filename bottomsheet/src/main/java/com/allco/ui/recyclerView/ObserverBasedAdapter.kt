package com.allco.ui.recyclerView

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.allco.ui.bottomsheet.BR

open class ObserverBasedAdapter(private val data: ItemList, private val dialogInterface: DialogInterface) : RecyclerView.Adapter<ObserverBasedAdapter.ViewHolder>() {

    class ItemList : ObservableArrayList<Item>()

    interface Item {
        @get:LayoutRes
        val layout: Int
        val binder: ((ViewDataBinding, DialogInterface) -> Unit)
            get() = { binding, _ ->
                binding.setVariable(BR.model, Item@ this)
            }
    }

    init {
        data.addOnListChangedCallback(object : android.databinding.ObservableList.OnListChangedCallback<ItemList>() {
            override fun onChanged(sender: ItemList?) = notifyDataSetChanged()
            override fun onItemRangeRemoved(sender: ItemList?, positionStart: Int, itemCount: Int) = notifyItemRangeRemoved(positionStart, itemCount)
            override fun onItemRangeMoved(sender: ItemList?, fromPosition: Int, toPosition: Int, itemCount: Int) = notifyDataSetChanged()
            override fun onItemRangeInserted(sender: ItemList?, positionStart: Int, itemCount: Int) = notifyItemRangeInserted(positionStart, itemCount)
            override fun onItemRangeChanged(sender: ItemList?, positionStart: Int, itemCount: Int) = notifyItemRangeChanged(positionStart, itemCount)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].binder(holder.binding, dialogInterface)
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = data[position].layout

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}
