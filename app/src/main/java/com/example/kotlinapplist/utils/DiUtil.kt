package com.example.kotlinapplist.utils

import android.content.Context
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.repo.PreferencesRepository
import com.example.kotlinapplist.repo.implementation.ItemRepositoryImpl
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl

object DiUtil {

    private lateinit var contextProvider: () -> Context
    val itemsRepository by lazy {
        createItemsRepository()
    }
    val itemsPreferences by lazy {
        createPreferencesRepository()
    }

    fun init(context: Context) {
        contextProvider = { context }
    }

    private fun createItemsRepository(): ItemRepository {
        return ItemRepositoryImpl()
    }
    private fun createPreferencesRepository(): PreferencesRepository{
        return PreferencesRepositoryImpl(contextProvider(), Consts.PREFERENCE_FILE_NAME)
    }

}