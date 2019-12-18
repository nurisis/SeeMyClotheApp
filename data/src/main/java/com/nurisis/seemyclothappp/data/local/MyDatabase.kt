package com.nurisis.seemyclothappp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun cartDao() : CartDao

    companion object{

        @Volatile
        private var INSTANCE : MyDatabase? = null

        fun getDatabase(context: Context) : MyDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }

        }

    }
}