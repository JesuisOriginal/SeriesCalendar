package com.milenialsatwork.seriescalendar.model.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resourses: Resources) {
    private val initialCardList: List<Series> = mutableListOf()
    private val cardsLivedata = MutableLiveData(initialCardList)
        get() = field


    /**
     * Adds Card to live data
     */
    fun addCard(card: Series) {
        val currentList = cardsLivedata.value
        if (currentList == null) {
            cardsLivedata.postValue(listOf(card))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, card);
            cardsLivedata.postValue(updatedList)
        }
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

    fun getCardsList(): LiveData<List<Series>> {
        return cardsLivedata
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}