package com.example.kotlinapplist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.utils.DiUtil

class ItemViewModel : ViewModel() {
    private val mutableItem: MutableLiveData<Item> = MutableLiveData()
    val item: LiveData<Item> = mutableItem

    fun loadItem(itemId: Int){
        mutableItem.postValue(DiUtil.itemsRepository.getItemById(itemId))
    }
}