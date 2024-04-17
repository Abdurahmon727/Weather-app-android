package com.example.composeapp.core.domain


sealed interface Either<T> {
    class Right<T>(val data: T) : Either<T>
    class Left<T>(val errorMessage: Message) : Either<T>

}


data class Message(val message : String = "",val messageResId : Int? = null)