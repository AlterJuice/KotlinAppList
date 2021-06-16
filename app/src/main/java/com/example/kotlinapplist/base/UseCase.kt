package com.example.kotlinapplist.base

interface UseCase<Action, State> {

    fun canHandle(action: Action): Boolean
    operator fun invoke(action: Action, state: State): Action

}