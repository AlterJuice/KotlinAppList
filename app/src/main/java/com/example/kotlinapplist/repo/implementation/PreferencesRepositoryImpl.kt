package com.example.kotlinapplist.repo.implementation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.repo.PreferencesRepository

class PreferencesRepositoryImpl(
    private val context: Context,
    private val preferencesFilename: String
) : PreferencesRepository {

    override fun setLastItemId(itemId: Int) {
        getPrefs().edit().putInt(Consts.preferenceKeyItemId, itemId).apply()
    }

    override fun clearLastItemId() {
        getPrefs().edit().remove(Consts.preferenceKeyItemId).apply()
    }

    override fun getLastItemId(): Int {
        return getPrefs().getInt(Consts.preferenceKeyItemId, -1)
    }

    private fun getPrefs(): SharedPreferences{
        return context.getSharedPreferences(preferencesFilename, MODE_PRIVATE)
    }
}