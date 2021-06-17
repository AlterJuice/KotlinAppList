package com.example.kotlinapplist.ui.details

import com.example.kotlinapplist.data.Item

sealed class ItemAction {
    object None : ItemAction()
    data class LoadItem(val itemId: Int) : ItemAction()
    data class ItemLoaded(val item: Item?) : ItemAction()

}