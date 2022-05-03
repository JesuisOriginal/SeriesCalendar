package com.milenialsatwork.seriescalendar.model.data

import androidx.lifecycle.MutableLiveData

interface SeriesDao {
    fun fetchSeries(): MutableLiveData<Series>
}