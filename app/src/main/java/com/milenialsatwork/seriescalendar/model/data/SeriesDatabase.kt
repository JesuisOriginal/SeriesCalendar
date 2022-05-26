package com.milenialsatwork.seriescalendar.model.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Series::class], version = 1, exportSchema = false)
abstract class SeriesDatabase: RoomDatabase() {
    abstract fun seriesDao(): SeriesDao
//
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SeriesDatabase? = null

        fun getDatabase(context: Context): SeriesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SeriesDatabase::class.java,
                    "series_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}