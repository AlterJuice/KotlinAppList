package com.example.kotlinapplist.utils

import android.content.Context
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.repo.ItemRepository
import com.example.kotlinapplist.repo.PreferencesRepository
import com.example.kotlinapplist.repo.implementation.ItemRepositoryImpl
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl
import com.example.kotlinapplist.usecase.LoadItemListUseCase
import com.example.kotlinapplist.usecase.LoadItemUseCase
import com.example.kotlinapplist.usecase.RemoveItemUseCase
import com.example.kotlinapplist.usecase.SaveItemIdUseCase

object DiUtil {

    private lateinit var contextProvider: () -> Context

    val itemsRepository by lazy {
        createItemsRepository()
    }
    val itemsPreferences by lazy {
        createPreferencesRepository()
    }


    val loadItemListUseCase by lazy {
        LoadItemListUseCase(itemsRepository)
    }

    val saveItemIdUseCase by lazy {
        SaveItemIdUseCase(itemsPreferences)
    }

    val removeItemUseCase by lazy{
        RemoveItemUseCase()
    }


    val loadItemUseCase by lazy{
        LoadItemUseCase(itemsRepository)
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