package com.example.kotlinapplist.repo

import com.example.kotlinapplist.data.Item

interface ItemRepository {

     fun getItems(): List<Item>
     fun getItemById(id: Int): Item?
     fun addItem(item: Item)

}