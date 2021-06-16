package com.example.kotlinapplist.ui.details

import com.example.kotlinapplist.base.Reducer
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListState

class ItemReducer: Reducer<ItemAction, ItemState> {

    override val initialState = ItemState( null)


    override fun reduce(action: ItemAction, state: ItemState): ItemState {
        return when(action) {
            ItemAction.None -> state
            is ItemAction.LoadItem -> state
            is ItemAction.ItemLoaded -> state.copy(item = action.item)
            ItemAction.ItemRemoved -> state.copy(item = null)
        }
    }
}