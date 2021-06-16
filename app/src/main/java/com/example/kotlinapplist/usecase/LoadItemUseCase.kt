package com.example.kotlinapplist.usecase

import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.ui.details.ItemAction
import com.example.kotlinapplist.ui.details.ItemState
import com.example.kotlinapplist.ui.list.ItemListState

class LoadItemUseCase(
    private val repository: ItemRepository

    ) : UseCase<ItemAction, ItemState>{

    override fun canHandle(action: ItemAction): Boolean {
        return action is ItemAction.LoadItem
    }

    override fun invoke(action: ItemAction, state: ItemState): ItemAction {
        if (action is ItemAction.LoadItem){
            return ItemAction.ItemLoaded(repository.getItemById(action.itemId))
        }
        return ItemAction.None
    }
}