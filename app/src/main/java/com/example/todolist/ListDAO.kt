package com.example.todolist

import androidx.room.*

@Dao
interface ListDAO {
    @Insert
    fun addItem(listEntity: ListEntity)

    @Query("SELECT * FROM todo_list")
    fun getAllItem(): List<ListEntity>

    @Delete
    fun deleteItem(listEntity: ListEntity)

    @Update
    fun updateItem(listEntity: ListEntity)
}