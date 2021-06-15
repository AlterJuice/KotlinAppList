package com.example.kotlinapplist

object ItemContent {

    val ITEMS: MutableList<Item> = ArrayList();
    val ITEMS_MAP: MutableMap<Int, Item> = HashMap();

    const val MAX_COUNT = 20;

    init {
        for (i in 0..MAX_COUNT){
            addItem(createItem(i))
        }
    }
    private fun createItem(id: Int): Item{
        return Item(id, "Name of $id", "Description of $id")
    }

    private fun addItem(item: Item){
        ITEMS.add(item);
        ITEMS_MAP[item.id] = item;
    }

    data class Item(val id: Int, val name: String, val description: String){
        override fun toString(): String {
            return "Item($id, $name, $description)"
        }
    }

}