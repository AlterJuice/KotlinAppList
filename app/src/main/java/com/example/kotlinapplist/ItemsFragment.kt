package com.example.kotlinapplist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager


class ItemsFragment : Fragment(), OnItemClick {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = ItemsRecyclerViewAdapter(ItemContent.ITEMS, this@ItemsFragment);
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemsFragment()
    }

    override fun singleClick(item: ItemContent.Item) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, DetailedItemFragment.newInstance(item.id), Consts.DETAILED_TAG)
            .addToBackStack(Consts.DETAILED_TAG)
            .commit()
        saveItemIdToPrefs(item.id)
    }

    private fun saveItemIdToPrefs(itemID: Int){
        with(requireActivity() as MainActivity, {
            addPrefIntValue(Consts.preferenceKeyItemId, itemID)
        })
    }
}