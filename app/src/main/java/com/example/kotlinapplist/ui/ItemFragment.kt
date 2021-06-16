package com.example.kotlinapplist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.databinding.FragmentDetailedItemBinding
import com.example.kotlinapplist.viewmodels.ItemViewModel


class ItemFragment : Fragment() {
    private lateinit var binding: FragmentDetailedItemBinding
    private val model by lazy{
        ViewModelProvider(this)[ItemViewModel::class.java]
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
        model.item.observe(viewLifecycleOwner, { showItem(it) })
        model.loadItem(itemId)
        // model.getItem().postValue(DiUtil.itemsRepository.getItemById(itemId))
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

    fun showItem(item: Item) {
        binding.itemDescription.text = item.description
        binding.itemName.text = item.name
        binding.itemID.text = item.id.toString()
    }
}