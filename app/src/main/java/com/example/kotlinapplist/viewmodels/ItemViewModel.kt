package com.example.kotlinapplist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapplist.base.BaseViewModel
import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.ui.details.ItemAction
import com.example.kotlinapplist.ui.details.ItemReducer
import com.example.kotlinapplist.ui.details.ItemState
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListState
import com.example.kotlinapplist.usecase.LoadItemUseCase
import com.example.kotlinapplist.utils.DiUtil

class ItemViewModel(
    useCases: Set<UseCase<ItemAction, ItemState>>
) : BaseViewModel<ItemAction, ItemState>(
    reducer = ItemReducer(),
    useCases = useCases
) {

    fun loadItem(itemId: Int){
        action(ItemAction.LoadItem(itemId))
    }

}