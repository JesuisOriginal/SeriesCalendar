package com.milenialsatwork.seriescalendar.viewmodel.card

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.data.SeriesDatabase
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository

@SuppressLint("StaticFieldLeak")
class CreateSeriesViewModel(
    private var context: Context
): ViewModel() {

    companion object {
        private var TAG = "CreateSeriesViewModel"
    }

    private lateinit var seriesCardRepository: SeriesCardRepository
    private lateinit var nameTextView: EditText
    private lateinit var chapterTextView: EditText


    fun getSeriesFromView(view: View) {
        val db = SeriesDatabase.getDatabase(context)
        seriesCardRepository = SeriesCardRepository(db.seriesDao())
        nameTextView = view.findViewById(R.id.name_input_field)
        chapterTextView = view.findViewById(R.id.chapter_input_field)

        seriesCardRepository
            .add(
                Series(
                    nameTextView.text.toString()
                ).also {
                    it.lastChapter = chapterTextView.text.toString()
                    it.image = R.mipmap.vegeto
                    it.lastChapter = "28"
                }
        )


    }


}