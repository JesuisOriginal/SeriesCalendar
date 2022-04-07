package com.milenialsatwork.seriescalendar.viewmodel.card

import android.content.Context
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.data.DataSource

class CardViewModel(context: Context, val dataSource: DataSource) : ViewModel() {
    companion object {
        val TAG: String = "CardViewModel"
    }

    val cardsLiveData = dataSource.getCardsList()

    //    private var image: Image = vege
    private var seriesName: String = "Name Placeholder"
        get() = field
        set(name: String) { field = name }
    private var lastChapter: String = "LastCH Placeholder"
        get() = field
        set(lastChapter: String) { field = lastChapter}
    private var lastUpdatedIn: String = "Updated Placeholder"
        get() = field
        set(update: String) { field = update}

    fun addNewSeries(seriesName: String, lastChapter: String, lastUpdated: String) {
        if (seriesName == null) {
            return
        }

        val newSeries = Series(
            seriesName,
            lastUpdated,
            lastChapter,
            R.mipmap.vegeto
        )
        dataSource.addCard(newSeries)
    }

    fun addNewSeries() = addNewSeries(seriesName, lastChapter, lastUpdatedIn)


    private fun onImageClicked() {
//        TODO: Make create a popup with the image, and a go back button, change image button.
    }
    private fun onCardClicked() {
        // TODO: Make it open the prefered browser/app in the page of the series, of the selected preferred source
    }
    private fun onCardSwipeLeft() {
        // TODO: Make appear some options: Delete, Edit?
    }
    private fun onSeriesNameTextClicked() {
        // TODO: Open a dialog to edit the text
    }

    // TODO: Make Beautiful soup work here to scrap these data
    // TODO: MyAnimeListAPI ? Integrate : Ignore this
    private fun onLastChapterTextClicked() {}
    private fun onLastUploadedInTextClicked() {}


}