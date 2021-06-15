package com.example.kotlinapplist.ui.items

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapplist.*
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.repo.implementation.ItemRepositoryImpl
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl
import com.example.kotlinapplist.ui.MainActivity
import com.example.kotlinapplist.ui.details.DetailedItemFragment


class ItemsFragment : Fragment(), ItemsFragmentView {

    private val presenter by lazy {
        ItemsPresenter()
    }
    private val adapter by lazy {
        ItemsRecyclerViewAdapter(listOf()) {
            singleClick(it)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        presenter.attach(this)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view is RecyclerView) {
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = adapter
        }
        presenter.requestItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemsFragment()
    }

    override fun displayItems(items: List<Item>) {
        adapter.setItems(items)
    }

    private fun singleClick(item: Item?) {
        if (item != null) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment, DetailedItemFragment.newInstance(item.id),
                    Consts.DETAILED_TAG
                )
                .addToBackStack(Consts.DETAILED_TAG)
                .commit()
            presenter.saveItemId(item.id)
        }
    }
}