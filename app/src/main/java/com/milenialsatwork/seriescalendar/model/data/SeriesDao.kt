package com.milenialsatwork.seriescalendar.model.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {
    @Query("SELECT * from series_table")
    fun fetchSeries(): List<Series>
    @Query("SELECT * FROM series_table ORDER BY name ASC")
    fun fetchAlphabetizedSeries(): List<Series>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg  series: Series)
    @Delete
    fun delete(vararg series: Series)
    @Update
    fun updateSeries(vararg series: Series)
}