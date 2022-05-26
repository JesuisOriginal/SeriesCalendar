package com.milenialsatwork.seriescalendar.model.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository

class DataSource(private val resourses: SeriesCardRepository) {
    private val initialCardList = resourses.seriesCardsList
    private val cardsLivedata = MutableLiveData(initialCardList)
        get() = field


    /**
     * Adds Card to live data
     */
//    fun addCard(card: Series) {
//        val currentList = cardsLivedata.value
//        if (currentList == null) {
//            cardsLivedata.postValue(mutableListOf(card))
//        } else {
//            val updatedList = currentList.toMutableList()
//            updatedList.add(0, card);
//            cardsLivedata.postValue(updatedList)
//        }
//    }

    fun addCard(card: Series) {
        resourses.add(card)
    }

    /**
     * Remove card from livedata
     */
    fun removeCard(card: Series) {
        val currList = cardsLivedata.value
        if (currList != null) {
            val updateList = currList.toMutableList()
            updateList.remove(card)
            cardsLivedata.postValue(updateList)
        }
    }

    fun getCardsList(): LiveData<MutableList<Series>> {
        return cardsLivedata
    }

    fun getCardsRepository(): SeriesCardRepository = resourses

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: SeriesCardRepository): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}