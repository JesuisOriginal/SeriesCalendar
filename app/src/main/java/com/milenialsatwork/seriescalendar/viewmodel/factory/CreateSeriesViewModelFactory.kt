package com.milenialsatwork.seriescalendar.viewmodel.factory

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.milenialsatwork.seriescalendar.model.data.DataSource
import com.milenialsatwork.seriescalendar.viewmodel.card.CardViewModel
import com.milenialsatwork.seriescalendar.viewmodel.card.CreateSeriesViewModel
import java.lang.IllegalArgumentException

class CreateSeriesViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateSeriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateSeriesViewModel(
                context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}