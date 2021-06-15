package com.example.kotlinapplist.ui.items

import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.repo.PreferencesRepository
import com.example.kotlinapplist.ui.MainActivity
import com.example.kotlinapplist.utils.DiUtil

class ItemsPresenter{

    private var view: ItemsFragmentView? = null

    fun attach(view: ItemsFragmentView) { this.view = view }

    fun detach() { view = null }

    fun requestItems() {
        with(DiUtil.itemsRepository.getItems(), {
            view?.displayItems(this)
        })
    }

    fun saveItemId(itemID: Int) {
        DiUtil.itemsPreferences.setLastItemId(itemID)
    }

}