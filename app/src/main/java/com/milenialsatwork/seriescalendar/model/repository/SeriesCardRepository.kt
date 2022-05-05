package com.milenialsatwork.seriescalendar.model.repository

import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import java.util.*

class SeriesCardRepository {
    val seriesCardsList: MutableList<Series> = mutableListOf()
        get() = field

    companion object {
        private const val TAG = "SeriesCardRepository"
    }
    fun add(card: Series)  {
        SCLog.d(TAG,"Adding card: $card")
        seriesCardsList.add(card)
    }

    fun remove(card: Series) = seriesCardsList.remove(card)

    fun remove(position: Int) = seriesCardsList.removeAt(position)

    fun swap(starterPosition: Int, finalPosition: Int) {
        Collections.swap(seriesCardsList, starterPosition, finalPosition)
    }

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