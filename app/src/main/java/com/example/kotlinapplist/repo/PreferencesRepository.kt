package com.example.kotlinapplist.repo


interface PreferencesRepository {

    fun setLastItemId(itemId: Int)
    fun clearLastItemId()
    fun getLastItemId(): Int

}