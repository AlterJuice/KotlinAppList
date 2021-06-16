package com.example.kotlinapplist.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapplist.*
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.ui.details.ItemFragment
import com.example.kotlinapplist.ui.details.ItemState
import com.example.kotlinapplist.utils.DiUtil
import com.example.kotlinapplist.viewmodels.ItemListViewModel


class ItemListFragment : Fragment() {

    private val model by lazy{
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ItemListViewModel(
                    useCases = setOf(
                        DiUtil.loadItemListUseCase
                    )
                ) as T
            }

        }).get(ItemListViewModel::class.java)
    }
    private val adapter by lazy {
        ItemsRecyclerViewAdapter(listOf()) {
            singleClick(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = adapter
        }
        model.state.observe(viewLifecycleOwner, {
            handleState(it)
        })
        model.loadItems()
    }

    private fun handleState(newState: ItemListState) {
        adapter.setItems(newState.items)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemListFragment()
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
            // DiUtil.itemsPreferences.setLastItemId(item.id)
            model.saveLastItemId(item.id)
        }
    }
}