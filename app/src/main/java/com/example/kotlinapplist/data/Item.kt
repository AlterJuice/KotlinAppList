package com.example.kotlinapplist.data

data class Item(val id: Int, val name: String, val description: String)
{
    override fun toString(): String {
        return "Item($id, $name, $description)"
    }
    companion object{
        fun createNewItem(id: Int) : Item {
            return Item(id, "Name of $id", "Description of $id")
        }
    }
}