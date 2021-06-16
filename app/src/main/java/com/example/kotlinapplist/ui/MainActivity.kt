package com.example.kotlinapplist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlinapplist.*
import com.example.kotlinapplist.data.Consts
import com.example.kotlinapplist.repo.implementation.PreferencesRepositoryImpl
import com.example.kotlinapplist.utils.DiUtil

class MainActivity : AppCompatActivity() {
    lateinit var prefs: PreferencesRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DiUtil.init(context = this)
        prefs = PreferencesRepositoryImpl(applicationContext, Consts.PREFERENCE_FILE_NAME)
        val savedItemId = prefs.getLastItemId()
        if (savedItemId == -1)
            replaceFragment(ItemListFragment.newInstance())
        else {
            replaceFragment(ItemFragment.newInstance(savedItemId))
            prefs.clearLastItemId()
        }
        startService(Intent(this, ItemService::class.java))
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1 && supportFragmentManager.fragments[0] is ItemFragment){
            replaceFragment(ItemListFragment.newInstance())
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
        replaceFragment(ItemFragment.newInstance(prefs.getLastItemId()))
    }
}