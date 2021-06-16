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
        return action is ItemListAction.LoadItems ||
                action is ItemListAction.SaveItemId ||
                action is ItemListAction.RemoveItem ||
                action is ItemListAction.ItemRemoved
    }

    override fun invoke(action: ItemListAction, state: ItemListState): ItemListAction {
        if (action is ItemListAction.LoadItems) {
            return ItemListAction.ItemsLoaded(repository.getItems())
        }else if (action is ItemListAction.SaveItemId){
            preferences.setLastItemId(state.itemId)
            return ItemListAction.ItemIdSaved(state.itemId)
        }else if (action is ItemListAction.RemoveItem){
            if (repository.getItems().size >= 1){
                repository.removeItemById(state.itemId)
                return ItemListAction.ItemRemoved(state.itemId)
            }
            return ItemListAction.ItemsAreEmpty
        }else if (action is ItemListAction.ItemRemoved){
            if (repository.getItems().size == 0){
                return ItemListAction.ItemsAreEmpty
            }
        }
        Log.d("LoadItemsUseCaseInvoke", "Another ItemListAction type got")
        return ItemListAction.None
    }
}