package com.example.kotlinapplist.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Action, State>(
    private val reducer: Reducer<Action, State>,
    private val useCases: Set<UseCase<Action, State>>
) : ViewModel() {

    private val mutableState = MutableLiveData(reducer.initialState)
    val state: LiveData<State> = mutableState

    protected fun action(action: Action) {
        mutableState.value = reducer.reduce(action, mutableState.value!!)
        useCases.filter {
            it.canHandle(action)
        }.forEach {
            val result = it.invoke(action, mutableState.value!!)
            action(result)
        }
    }

}