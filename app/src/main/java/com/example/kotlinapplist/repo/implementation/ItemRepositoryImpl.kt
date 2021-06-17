package com.example.kotlinapplist.repo.implementation

import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.data.Item

import com.example.kotlinapplist.repo.ItemRepository

class ItemRepositoryImpl: ItemRepository {
    private val items = ArrayList<Item>()
    private val itemsMap: MutableMap<Int, Item> = HashMap()

    init {
        createItems()
    }

    override fun getItems(): List<Item> {
        return items
    }

    override fun getItemById(id: Int): Item? {
        return itemsMap[id]
    }

    override fun addItem(item: Item) {
        items.add(item)
        itemsMap[item.id] = item
    }

    override fun removeItemById(id: Int) {
        items.remove(itemsMap.remove(id))

    }

    override fun createItems() {
        for (i in 0..Consts.MAX_ITEMS_COUNT){
            addItem(Item.createNewItem(i))
        }
    }
}