package com.example.kotlinapplist.ui.list

import com.example.kotlinapplist.data.Item

data class ItemListState(
    val isLoading: Boolean,
    val items: List<Item>
)
