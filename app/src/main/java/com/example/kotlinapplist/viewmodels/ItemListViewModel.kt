package com.example.kotlinapplist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapplist.data.Item
import com.example.kotlinapplist.utils.DiUtil

class ItemListViewModel: ViewModel() {

    private val mutableItems: MutableLiveData<List<Item>> = MutableLiveData()
    val items: LiveData<List<Item>> = mutableItems

    fun loadItems(){
        mutableItems.postValue(DiUtil.itemsRepository.getItems())
    }

    fun saveLastItemId(itemId: Int){
        DiUtil.itemsPreferences.setLastItemId(itemId)
    }

}