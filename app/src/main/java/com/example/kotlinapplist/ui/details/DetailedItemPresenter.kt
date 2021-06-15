package com.example.kotlinapplist.ui.details

import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.utils.DiUtil

class DetailedItemPresenter(
    private val itemsRepository: ItemRepository
) {
    private var view: DetailedItemFragmentView? = null


    fun attach(view: DetailedItemFragmentView) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    fun requestItemById(itemId: Int) {
        itemsRepository.getItemById(itemId)?.also {
            view?.showItem(it)
        }
    }

}