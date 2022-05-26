package com.milenialsatwork.seriescalendar.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milenialsatwork.seriescalendar.model.data.DataSource
import com.milenialsatwork.seriescalendar.model.data.SeriesDatabase
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.viewmodel.card.CardViewModel
import java.lang.IllegalArgumentException

class CardViewModelFactory (
    private val context: Context
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardViewModel(
                context,
                dataSource = DataSource.getDataSource(
                    SeriesCardRepository(
                        SeriesDatabase.getDatabase(context).seriesDao()
                    )
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    fun createFromContext(): CardViewModel {
        return CardViewModel(
            context,
            dataSource = DataSource.getDataSource(
                SeriesCardRepository(
                    SeriesDatabase.getDatabase(context).seriesDao()
                )
            )
        ) as CardViewModel
    }
}