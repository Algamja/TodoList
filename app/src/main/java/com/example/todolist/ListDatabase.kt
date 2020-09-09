package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ListEntity::class], version = 1)
abstract class ListDatabase : RoomDatabase() {
    abstract fun listDao(): ListDAO

    companion object {
        var INSTANCE: ListDatabase? = null

        fun getInstance(context: Context): ListDatabase? {
            if (INSTANCE == null) {
                synchronized(ListDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ListDatabase::class.java,
                        "list.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}