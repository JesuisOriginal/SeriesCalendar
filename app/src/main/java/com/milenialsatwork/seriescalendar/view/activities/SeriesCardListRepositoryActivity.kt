package com.milenialsatwork.seriescalendar.view.activities

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.databinding.SeriesCardsListRepositoryActivityBinding
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import com.milenialsatwork.seriescalendar.view.adapter.CardAdapter
import com.milenialsatwork.seriescalendar.view.adapter.SeriesListAdapter
import com.milenialsatwork.seriescalendar.view.ui.dialog.InputDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SeriesCardListRepositoryActivity : Activity(){

    companion object {
        private var TAG: String = "SeriesCardListRepositoryActivity"
    }

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: SeriesCardsListRepositoryActivityBinding

    // Todo: to ViewModel Start
    private lateinit var seriesRepository: SeriesCardRepository
    private lateinit var cardListView: ListView
    // Todo: to ViewModel end

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SeriesCardsListRepositoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        seriesRepository = SeriesCardRepository()
        cardListView = findViewById(R.id.card_list_view)
        cardListView.adapter = SeriesListAdapter(this,
            SeriesCardRepository().also { it.populateWithProps(10) })

        binding.addFab.setOnClickListener { view ->
            SCLog.d(TAG, "addFab.setOnClickListener: clicked")
            Snackbar.make(view, "Adding new series", Snackbar.LENGTH_LONG).show()
            addNewTrackedSeries(view)
        }
//        binding.addFab.setImageResource(R.mipmap.fab_icon_round)
    }


    // TODO: Move methods below to ViewModel of CardActivity
    private fun addNewTrackedSeries(view: View) {
        SCLog.d(TAG, "addNewTrackedSeries: creating Series")
        var series: Series = Series(
            "None",
            "28",
            "yesterday",
            getImage()
        )
        SCLog.d(TAG, "addNewTrackedSeries: creating input dialog")
        var inputDialog: InputDialog = InputDialog(this)
        inputDialog
            .setCallbackFunction {
                doSetSeriesName(series, it.toString())
                inputDialog.dismiss()
            }
            .show()



//        getLastUpdated()
//        getLastChapter()
//        getImage()

        // Todo: Notify activity that list changed <> Reload activity/list
    }

    private fun addSeries(series: Series) {
        SCLog.d(TAG, "addNewTrackedSeries: Adding $series")
        seriesRepository.add(series)
        printAllSeries()
        updateList()
    }

    private fun doSetSeriesName(seriesCard: Series, name:String) = runBlocking {
        launch {
            delay(500L)
            SCLog.d(TAG, "doGetSeriesName: got name ${seriesCard.name}")
            delay(300L)
            addSeries(seriesCard)
        }
        seriesCard.name = name
    }

    private fun printAllSeries() {
        for (series: Series in seriesRepository.seriesCardsList) {
            SCLog.d(TAG, "printAllSeries: $series")
        }
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

    private fun updateList() {
        cardListView.refreshDrawableState()
    }

    private fun onNewTrackedSeriesAdded(view: View) {
        SCLog.d(TAG, "onNewTrackedSeriesAdded")
        Snackbar.make(view, "Tracking new Series", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}

