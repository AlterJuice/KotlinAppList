package com.example.kotlinapplist

import com.example.kotlinapplist.repo.implementation.ItemRepositoryImpl
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl

class AppComponentImpl : AppComponent {


    override fun getRepo(): ItemRepositoryImpl {
        TODO("Not yet implemented")
    }

    override fun getPrefs(): PreferencesRepositoryImpl {
        TODO("Not yet implemented")
    }
}