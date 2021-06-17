package com.example.kotlinapplist.usecase

import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.repo.PreferencesRepository
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListState

class SaveItemIdUseCase(
    private val preferences: PreferencesRepository
) : UseCase<ItemListAction, ItemListState> {
    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.SaveItemId
    }

    override fun invoke(action: ItemListAction, state: ItemListState): ItemListAction {
        if (action is ItemListAction.SaveItemId){
            preferences.setLastItemId(action.itemId)
        }
        return ItemListAction.None
    }
}