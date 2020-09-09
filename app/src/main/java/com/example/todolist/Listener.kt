package com.example.todolist

interface Listener {
    fun onFinishListener(listEntity: ListEntity)
    fun onDeleteListener(listEntity: ListEntity)
}