package com.example.kotlinapplist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlinapplist.data.Item

import com.example.kotlinapplist.databinding.FragmentItemBinding

class ItemsRecyclerViewAdapter(
    private var values: List<Item>,
    private val clickListener: (Item) -> Unit,
    private val clearClickListener : (Item) -> Unit
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
        holder.bind(item, clickListener, clearClickListener)
    }

    override fun getItemCount(): Int = values.size

    fun setItems(newItems: List<Item>) {
        values = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, onItemClick: (Item) -> Unit, onClearClick: (Item) -> Unit){
            binding.root.setOnClickListener { onItemClick(item) }
            binding.clearView.setOnClickListener{ onClearClick(item) }
            binding.itemID.text = item.id.toString()
        }

    }

}