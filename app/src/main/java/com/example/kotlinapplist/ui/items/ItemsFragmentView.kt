package com.example.kotlinapplist.ui.items

import com.example.kotlinapplist.data.Item

interface ItemsFragmentView {
    fun displayItems(items: List<Item>)
}