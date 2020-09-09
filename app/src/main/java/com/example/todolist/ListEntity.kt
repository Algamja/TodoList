package com.example.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    var item:String,
    var finish:Boolean = true
)