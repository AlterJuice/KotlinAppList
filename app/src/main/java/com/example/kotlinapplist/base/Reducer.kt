package com.example.kotlinapplist.base

interface Reducer<Action, State> {
    val initialState: State
    fun reduce(action: Action, state: State): State


}