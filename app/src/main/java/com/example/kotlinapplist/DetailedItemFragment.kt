package com.example.kotlinapplist

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.preference.PreferenceCategory
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.kotlinapplist.databinding.FragmentDetailedItemBinding


class DetailedItemFragment : Fragment() {
    private var paramItemId: Int? = null
    private lateinit var binding: FragmentDetailedItemBinding
    private val itemData: MutableLiveData<ItemContent.Item> = MutableLiveData()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailedItemBinding.inflate(inflater, container, false)
        arguments?.let {
            paramItemId = it.getInt(Consts.ARG_PARAM_ITEM_ID)
            val item = ItemContent.ITEMS_MAP[paramItemId]
            Log.d(Consts.ARG_PARAM_ITEM_ID, item.toString())
            itemData.postValue(item)
        }
        return binding.root;
    }

    private val observer: Observer<ItemContent.Item> = Observer{
        binding.itemDescription.text = it.description
        binding.itemName.text = it.name
        binding.itemID.text = it.id.toString()

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = getViewLifecycleOwner();
        itemData.observe(getViewLifecycleOwner(), observer)
    }

    companion object {
        @JvmStatic
        fun newInstance(itemID: Int) =
            DetailedItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(Consts.ARG_PARAM_ITEM_ID, itemID)
                }
            }
    }
}