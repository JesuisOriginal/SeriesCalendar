package com.milenialsatwork.seriescalendar.model.data

import androidx.lifecycle.MutableLiveData

class SeriesDaoImpl: SeriesDao {
    override fun fetchSeries(): MutableLiveData<Series> {
        var series = MutableLiveData<Series>()
        // Ler as series do room e mandar pra series
        return series
    }

}