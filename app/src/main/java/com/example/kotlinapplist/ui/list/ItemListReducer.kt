package com.example.kotlinapplist.ui.list

import com.example.kotlinapplist.base.Reducer

class ItemListReducer : Reducer<ItemListAction, ItemListState> {

    override val initialState = ItemListState(
        items = listOf(), -1
    )


    override fun reduce(action: ItemListAction, state: ItemListState): ItemListState {
        return when(action) {
            ItemListAction.None -> state
            ItemListAction.LoadItems -> state
            is ItemListAction.ItemsLoaded -> state.copy(items = action.items)
            is ItemListAction.SaveItemId -> state.copy(itemId = action.itemId)
            is ItemListAction.ItemIdSaved -> state
            is ItemListAction.ItemRemoved -> state
            ItemListAction.ItemsAreEmpty -> state.copy(items = listOf())
            is ItemListAction.RemoveItem -> state.copy(itemId = action.itemId)
        }
    }

}