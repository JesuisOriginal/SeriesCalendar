package com.milenialsatwork.seriescalendar.view.activities

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.databinding.SeriesCardsListRepositoryActivityBinding
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import com.milenialsatwork.seriescalendar.view.ui.dialog.InputDialog


class SeriesCardListRepositoryActivity : Activity(){

    companion object {
        private var TAG: String = "SeriesCardListRepositoryActivity"
    }

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: SeriesCardsListRepositoryActivityBinding

    // Todo: to ViewModel Start
    private lateinit var cardsDAO: SeriesCardRepository
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

        cardsDAO = SeriesCardRepository()
        cardListView = findViewById(R.id.card_list_view)

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
        var card: Series = Series(
            "None",
            "28",
            "yesterday",
            getImage(view)
        )

        var inputDialog: InputDialog = InputDialog(this)
        inputDialog
            .setCallbackFunction {
                SCLog.d(TAG, "Value = ${it.toString()}")
                card.name = it.toString()
                inputDialog.dismiss()
            }
            .show()
        SCLog.d(TAG, "got name ${card.name}")


//        while (getSeriesName(view, card) == "Got None" || card.name == "Got None") {
//            card.name = getSeriesName(view, card)
//        }
        SCLog.d(TAG, "Workaround end :()")
        getLastUpdated(view, true)
        getLastChapter(view, true)
        getImage(view)
        SCLog.d(TAG, "addNewTrackedSeries: Adding ${card.toString()}")
        cardsDAO.add(card)
        updateList()
        // Todo: Notify activity that list changed <> Reload activity/list
    }

    private fun getSeriesName(view: View, seriesCard: Series): String {
        var seriesName: String = "Got None"

        SCLog.d(TAG, "addnewTrackedSeries: name = $seriesName")
        var inputDialog: InputDialog = InputDialog(this)
        inputDialog
            .setCallbackFunction {
                SCLog.d(TAG, "Value = ${it.toString()}")
                seriesCard.name = it.toString()
                inputDialog.dismiss()
            }
            .show()

//        if (seriesName == "Got None") {
//            SCLog.d(TAG, "getSeriesName: Error getting name.")
//        }
        while (seriesName == "Got None")
           continue
        // delay(100)
        return seriesName
    }

    fun callbackFunction(value: Any): Any {
        return value
    }
    private fun getLastUpdated(view: View, retry: Boolean): String {
        return "Yesterday"
    }

    private fun getLastChapter(view: View, retry: Boolean): String {
        return "28"
    }

    private fun getImage(view: View): Int {
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

