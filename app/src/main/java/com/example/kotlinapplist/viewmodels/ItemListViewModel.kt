package com.example.kotlinapplist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapplist.base.BaseViewModel
import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListReducer
import com.example.kotlinapplist.ui.list.ItemListState
import com.example.kotlinapplist.utils.DiUtil

class ItemListViewModel(
    useCases: Set<UseCase<ItemListAction, ItemListState>>
): BaseViewModel<ItemListAction, ItemListState>(
    reducer = ItemListReducer(),
    useCases = useCases
) {

    fun loadItems(){
        action(ItemListAction.LoadItems)
    }

    fun saveLastItemId(itemId: Int){
        action(ItemListAction.SaveItemId(itemId))
    }

    fun removeItemById(itemId: Int){
        action(ItemListAction.RemoveItem(itemId))
    }
}