package com.example.kotlinapplist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlinapplist.databinding.FragmentItemBinding


class ItemsRecyclerViewAdapter(
    private val values: List<ItemContent.Item>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        private lateinit var item: ItemContent.Item


        fun bind(item: ItemContent.Item){
            this.item = item
            binding.root.setOnClickListener(this)
            binding.itemID.text = this.item.id.toString()
        }

        override fun onClick(v: View?) {
            clickListener.singleClick(item)
        }
    }

}