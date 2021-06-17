package com.example.kotlinapplist.usecase

import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListState

class RemoveItemUseCase(
) : UseCase<ItemListAction, ItemListState> {
    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.RemoveItem
    }

    override fun invoke(action: ItemListAction, state: ItemListState): ItemListAction {
        if (action is ItemListAction.RemoveItem){
            // repository.removeItemById(action.itemId)
            val mutableItems = state.items.toMutableList()
            mutableItems.removeIf { it.id == action.itemId }
            return ItemListAction.ItemsLoaded(mutableItems)
        }

        return ItemListAction.None
    }
}