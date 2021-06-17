package com.example.kotlinapplist.usecase

import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListState

class LoadItemListUseCase(
    private val repository: ItemRepository
) : UseCase<ItemListAction, ItemListState> {

    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.LoadItems
    }

    override fun invoke(action: ItemListAction, state: ItemListState): ItemListAction {
        return ItemListAction.ItemsLoaded(repository.getItems())
    }
}