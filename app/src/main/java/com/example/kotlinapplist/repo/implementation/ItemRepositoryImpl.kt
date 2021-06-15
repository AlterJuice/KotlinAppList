package com.example.kotlinapplist.repo.implementation

import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item

import com.example.kotlinapplist.repo.ItemRepository

class ItemRepositoryImpl: ItemRepository {
    private val items = ArrayList<Item>()
    private val itemsMap: MutableMap<Int, Item> = HashMap()

    init {
        createItems(Consts.MAX_ITEMS_COUNT)
    }

    override fun getItems(): List<Item> {
        if (items.size == 0)
            createItems(Consts.MAX_ITEMS_COUNT)
        return items
    }

    override fun getItemById(id: Int): Item? {
        return itemsMap[id]
    }

    override fun addItem(item: Item) {
        items.add(item)
        itemsMap[item.id] = item
    }

    private fun createItems(count: Int){
        for (i in 0..count){
            addItem(Item.createNewItem(i))
        }
    }
}