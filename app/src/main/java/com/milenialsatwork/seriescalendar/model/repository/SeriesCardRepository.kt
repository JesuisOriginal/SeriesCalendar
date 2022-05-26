package com.milenialsatwork.seriescalendar.model.repository

import androidx.annotation.WorkerThread
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.data.SeriesDao
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class SeriesCardRepository(private val seriesDao: SeriesDao) {
//    val seriesCardsList: MutableList<Series> = mutableListOf()
//        get() = field

    val seriesCardsList: MutableList<Series> = seriesDao.fetchSeries() as MutableList<Series>
        get() = field

    companion object {
        private const val TAG = "eSriesCardRepository"
    }

//    fun add(series: Series)  {
//        SCLog.d(TAG,"Adding card: $series")
//        seriesCardsList.add(card)
//
//    }

    fun add(series: Series)  {
        SCLog.d(TAG,"Adding card: $series")
        seriesDao.insertAll(series)
        seriesCardsList.add(series)
    }

//    fun remove(card: Series) = seriesCardsList.remove(card)

    fun remove(series: Series) {
        seriesDao.delete(series)
        seriesCardsList.remove(series)
    }

//    fun remove(position: Int) = seriesCardsList.removeAt(position)

//    fun swap(starterPosition: Int, finalPosition: Int) {
//        Collections.swap(seriesCardsList, starterPosition, finalPosition)
//    }

        fun getSerieByIndex(index: Int): Series? {

            if (seriesCardsList.size > 0){
                SCLog.d(TAG, "getSeriesByIndex: Index: $index, Size: $size")
                val ErorSeries = Series().also {
                    it.name = "Index Error, I'm a Dud"
                }
                return seriesCardsList.getOrElse(index) { null }
            }

            else {
                SCLog.d(TAG, "getSeriesByIndex: I should not be here :()")
                return null
            }
        }

        fun populateWithProps(size: Int) {
            for (i in 1..size) {
                add(Series())
            }
        }

        val size: Int
            get() = seriesCardsList.size
}