package com.example.kotlinapplist

import com.example.kotlinapplist.repo.implementation.ItemRepositoryImpl
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl

interface AppComponent {
    fun getRepo(): ItemRepositoryImpl
    fun getPrefs(): PreferencesRepositoryImpl
}