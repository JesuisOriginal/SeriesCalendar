package com.milenialsatwork.seriescalendar.model.data

import androidx.room.*


@Dao
interface AsyncSeriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg  series: Series)

    @Update
    suspend fun updateUsers(vararg series: Series)

    @Delete
    suspend fun deleteUsers(vararg series: Series)

    @Query("SELECT * FROM Series WHERE id = :id")
    suspend fun loadUserById(id: Int): Series

    // TODO: Select series by release day / by category
//    @Query("SELECT * from Series WHERE region IN (:regions)")
//    suspend fun loadUsersByRegion(regions: List<String>): List<Series>
}