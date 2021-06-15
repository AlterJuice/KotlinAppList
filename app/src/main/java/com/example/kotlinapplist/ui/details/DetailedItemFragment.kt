package com.example.kotlinapplist.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.databinding.FragmentDetailedItemBinding
import com.example.kotlinapplist.repo.implementation.ItemRepositoryImpl
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl
import com.example.kotlinapplist.ui.items.ItemsPresenter
import com.example.kotlinapplist.utils.DiUtil


class DetailedItemFragment : Fragment(), DetailedItemFragmentView {
    private lateinit var binding: FragmentDetailedItemBinding

    private val itemId by lazy {
        arguments?.getInt(Consts.ARG_PARAM_ITEM_ID) ?: -1
    }
    private val presenter by lazy {
        DetailedItemPresenter(DiUtil.itemsRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailedItemBinding.inflate(inflater, container, false)
        presenter.attach(this)
        return binding.root;
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.requestItemById(itemId)
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

    override fun showItem(item: Item) {
        binding.itemDescription.text = item.description
        binding.itemName.text = item.name
        binding.itemID.text = item.id.toString()
    }
}