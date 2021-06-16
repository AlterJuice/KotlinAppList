package com.example.kotlinapplist.ui.list

import com.example.kotlinapplist.data.Item

data class ItemListState(
    val items: List<Item>,
    val itemId: Int
)
