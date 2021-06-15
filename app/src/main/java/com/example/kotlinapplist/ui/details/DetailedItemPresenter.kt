package com.example.kotlinapplist.ui.details

import com.example.kotlinapplist.utils.DiUtil

class DetailedItemPresenter {
    private var view: DetailedItemFragmentView? = null

    fun attach(view: DetailedItemFragmentView) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    fun requestItemById(itemId: Int) {
        DiUtil.itemsRepository.getItemById(itemId)?.also {
            view?.showItem(it)
        }
    }

}