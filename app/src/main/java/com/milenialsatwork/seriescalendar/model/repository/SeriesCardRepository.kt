package com.milenialsatwork.seriescalendar.model.repository

import com.milenialsatwork.seriescalendar.model.data.Series
import java.util.*

class SeriesCardRepository {
    val seriesCardsList: MutableList<Series> = mutableListOf()
        get() = field

    fun add(card: Series) = seriesCardsList.add(card)

    fun remove(card: Series) = seriesCardsList.remove(card)

    fun remove(position: Int) = seriesCardsList.removeAt(position)

    fun swap(starterPosition: Int, finalPosition: Int) {
        Collections.swap(seriesCardsList, starterPosition, finalPosition)
    }

    fun getSerieByIndex(index: Int): Series {
        return seriesCardsList[index]
    }

    fun populateWithProps(size: Int) {
        for (i in 1..size) {
            add(Series())
        }
    }

    val size: Int
        get() = seriesCardsList.size
}