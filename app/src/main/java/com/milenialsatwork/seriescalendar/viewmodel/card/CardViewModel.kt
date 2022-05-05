package com.milenialsatwork.seriescalendar.viewmodel.card

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.data.DataSource
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import com.milenialsatwork.seriescalendar.view.ui.dialog.InputDialog
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CardViewModel(context: Context, private val dataSource: DataSource) : ViewModel() {
    companion object {
        val TAG: String = "CardViewModel"
    }

    val cardsLiveData = dataSource.getCardsList()
    fun getDataSource(): DataSource = dataSource

    //    private var image: Image = vege
    private var seriesName: String = "Name Placeholder"
    private var lastChapter: String = "LastCH Placeholder"
    private var lastUpdatedIn: String = "Updated Placeholder"

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
        SCLog.d(TAG, "addNewSeries: adding $newSeries")
        dataSource.addCard(newSeries)
    }

    fun addNewSeries() = addNewSeries(seriesName, lastChapter, lastUpdatedIn)

    fun addNewSeries(series: Series) {
        SCLog.d(TAG, "addNewSeries: adding $series")
        dataSource.addCard(series)
    }

    fun addNewTrackedSeries(view: View, parent: Activity, onCardCreatedCallback: () -> Unit) {
        SCLog.d(TAG, "addNewTrackedSeries: creating Series")
        var series: Series = Series(
            "None",
            "28",
            "yesterday",
            getImage()
        )
        SCLog.d(TAG, "addNewTrackedSeries: creating input dialog")
        var inputDialog: InputDialog = InputDialog(parent)
        inputDialog
            .setCallbackFunction {
                doSetSeriesName(series, it.toString(), onCardCreatedCallback)
                inputDialog.dismiss()
            }
            .show()

    }

    private fun addSeriesToDataSource(series: Series) {
        SCLog.d(TAG, "addNewTrackedSeries: Adding $series")
        dataSource.getCardsRepository().add(series)
    }

    private fun doSetSeriesName(seriesCard: Series, name:String, doUpdateList: () -> Unit) = runBlocking {
        launch {
            SCLog.d(TAG, "doGetSeriesName: got name ${seriesCard.name}")
            addSeriesToDataSource(seriesCard)
//            cardViewModel.addNewSeries(seriesCard)
            doUpdateList()
        }
        seriesCard.name = name
    }

    fun callbackFunction(value: Any): Any {
        return value
    }
    private fun getLastUpdated(): String {
        return "Yesterday"
    }

    private fun getLastChapter(): String {
        return "28"
    }

    private fun getImage(): Int {
        // TODO: Make it grab an image from camera or gallery
        return R.mipmap.pot
    }

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