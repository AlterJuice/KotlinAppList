package com.example.kotlinapplist.ui.items

import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.repo.PreferencesRepository

class ItemsPresenter(
    private val prefs: PreferencesRepository,
    private val itemRepo: ItemRepository
){

    private var view: ItemsFragmentView? = null

    fun attach(view: ItemsFragmentView) { this.view = view }

    fun detach() { view = null }

    fun requestItems() {
        with(itemRepo.getItems(), {
            view?.displayItems(this)
        })
    }

    fun saveItemId(itemID: Int) {
        prefs.setLastItemId(itemID)
    }

}