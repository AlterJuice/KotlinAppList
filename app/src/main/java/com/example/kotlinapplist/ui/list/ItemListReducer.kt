package com.example.kotlinapplist.ui.list

import com.example.kotlinapplist.base.Reducer

class ItemListReducer : Reducer<ItemListAction, ItemListState> {

    override val initialState = ItemListState(
        isLoading = false,
        items = listOf()
    )


    override fun reduce(action: ItemListAction, state: ItemListState): ItemListState {

        return when(action) {
            ItemListAction.None -> state
            ItemListAction.LoadItems -> state.copy(
                isLoading = true
            )
            is ItemListAction.SaveItemId -> state
            is ItemListAction.RemoveItem -> state

            is ItemListAction.ItemsLoaded -> state.copy(
                isLoading = false,
                items = action.items
            )
        }
    }

}