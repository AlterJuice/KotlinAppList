package com.example.kotlinapplist.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapplist.*
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.databinding.FragmentItemListBinding
import com.example.kotlinapplist.ui.details.ItemFragment
import com.example.kotlinapplist.ui.details.ItemState
import com.example.kotlinapplist.utils.DiUtil
import com.example.kotlinapplist.viewmodels.ItemListViewModel


class ItemListFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding

    private val model by lazy{
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ItemListViewModel(
                    useCases = setOf(
                        DiUtil.loadItemListUseCase,
                        DiUtil.removeItemUseCase,
                        DiUtil.saveItemIdUseCase
                    )
                ) as T
            }

        }).get(ItemListViewModel::class.java)
    }
    private val adapter by lazy {
        ItemsRecyclerViewAdapter(listOf(), { singleClick(it) }, { removeClick(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(context)
        model.state.observe(viewLifecycleOwner, {
            handleState(it)
        })
        binding.reloadButton.setOnClickListener {
            model.loadItems()
        }
        model.loadItems()
    }

    private fun handleState(newState: ItemListState) {
        val visibility: Int = if (newState.items.isNotEmpty()) View.INVISIBLE else View.VISIBLE
        binding.infoText.visibility = visibility
        binding.reloadButton.visibility = visibility
        adapter.setItems(newState.items)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemListFragment()
    }

    private fun removeClick(item: Item?): Boolean{
        if (item != null) {
            model.removeItemById(item.id)
            Toast.makeText(context, "Item with ID ${item.id} removed", Toast.LENGTH_SHORT).show()
        }
        return false
    }


    private fun singleClick(item: Item?) {
        if (item != null) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment, ItemFragment.newInstance(item.id),
                    Consts.DETAILED_TAG
                )
                .addToBackStack(Consts.DETAILED_TAG)
                .commit()
            model.saveLastItemId(item.id)
        }
    }
}