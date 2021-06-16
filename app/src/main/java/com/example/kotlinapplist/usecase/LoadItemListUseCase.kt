package com.example.kotlinapplist.usecase

import android.util.Log
import com.example.kotlinapplist.base.UseCase
import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.repo.PreferencesRepository
import com.example.kotlinapplist.ui.list.ItemListAction
import com.example.kotlinapplist.ui.list.ItemListState

class LoadItemListUseCase(
    private val repository: ItemRepository,
    private val preferences: PreferencesRepository
) : UseCase<ItemListAction, ItemListState> {

    override fun canHandle(action: ItemListAction): Boolean {
        return action is ItemListAction.LoadItems || action is ItemListAction.SaveItemId
    }

    override fun invoke(action: ItemListAction, state: ItemListState): ItemListAction {
        if (action is ItemListAction.LoadItems) {
            return ItemListAction.ItemsLoaded(repository.getItems())
        }else if (action is ItemListAction.SaveItemId){
            preferences.setLastItemId(state.savedItemId)
            return ItemListAction.ItemIdSaved(state.savedItemId)
        }
        Log.d("LoadItemsUseCaseInvoke", "Another ItemListAction type got")
        return ItemListAction.None
    }
}