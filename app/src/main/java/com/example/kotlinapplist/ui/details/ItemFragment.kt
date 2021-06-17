package com.example.kotlinapplist.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.databinding.FragmentDetailedItemBinding
import com.example.kotlinapplist.ui.list.ItemListState
import com.example.kotlinapplist.utils.DiUtil
import com.example.kotlinapplist.viewmodels.ItemListViewModel
import com.example.kotlinapplist.viewmodels.ItemViewModel


class ItemFragment : Fragment() {
    private lateinit var binding: FragmentDetailedItemBinding
    private val model by lazy{
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ItemViewModel(
                    useCases = setOf(
                        DiUtil.loadItemUseCase
                    )
                ) as T
            }

        }).get(ItemViewModel::class.java)
    }
    private val itemId by lazy {
        arguments?.getInt(Consts.ARG_PARAM_ITEM_ID) ?: -1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailedItemBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.state.observe(viewLifecycleOwner, {
            showItem(it.item)
        })
        model.loadItem(itemId)
    }

    companion object {
        @JvmStatic
        fun newInstance(itemID: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(Consts.ARG_PARAM_ITEM_ID, itemID)
                }
            }
    }

    fun showItem(item: Item?) {
        binding.itemDescription.text = item?.description
        binding.itemName.text = item?.name
        binding.itemID.text = item?.id.toString()
    }
}