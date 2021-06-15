package com.example.kotlinapplist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savedItemId = getSavedItemId();
        if (savedItemId == -1)
            replaceFragment(ItemsFragment.newInstance())
        else {
            replaceFragment(DetailedItemFragment.newInstance(savedItemId))
            removePrefValue(Consts.preferenceKeyItemId)
        }
        startService(Intent(this, ItemService::class.java))
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1 && supportFragmentManager.fragments[0] is DetailedItemFragment){
            replaceFragment(ItemsFragment.newInstance())
        }else {
            super.onBackPressed()
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment).
            commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        replaceFragment(DetailedItemFragment.newInstance(getSavedItemId()))
    }

    fun getSavedItemId(): Int{
        return getIntPrefValue(Consts.preferenceKeyItemId, -1)
    }
    fun getIntPrefValue(key: String, defValue: Int): Int {
        return getPrefs().getInt(key, defValue)
    }
    fun getStringPrefValue(key: String, defValue: String) : String? {
        return getPrefs().getString(key, defValue)
    }
    fun removePrefValue(key: String){
        getPrefs().edit().remove(key).apply()
    }
    fun addPrefIntValue(key: String, value: Int){
        getPrefs().edit().putInt(key, value).apply()
    }
    fun addPrefStringValue(key: String, value: String){
        getPrefs().edit().putString(key, value).apply()
    }
    fun getPrefs(): SharedPreferences{
        return getPrefs(this, Consts.PREFERENCE_FILE_NAME, MODE_PRIVATE)
    }
    companion object {
        fun getPrefs(context: Context, prefName: String, mode: Int): SharedPreferences {
            return context.getSharedPreferences(prefName, mode)
        }
    }
}