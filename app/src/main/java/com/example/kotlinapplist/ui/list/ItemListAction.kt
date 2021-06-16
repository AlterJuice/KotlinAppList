package com.example.kotlinapplist.ui.list

import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.ui.details.ItemAction

sealed class ItemListAction {
    object None : ItemListAction()
    object LoadItems : ItemListAction()
    data class ItemsLoaded(val items: List<Item>) : ItemListAction()
    data class ItemIdSaved(val itemId: Int) : ItemListAction()
    data class SaveItemId(val itemId: Int): ItemListAction()

}