package com.android.jio.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.jio.cache.converter.DataConverter

@Database(entities = [ResponseEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MainDB : RoomDatabase() {

    companion object {
        const val DB_NAME = "MainDB"
    }

    abstract fun mainDao(): MainDao
}