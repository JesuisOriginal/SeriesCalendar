package com.milenialsatwork.seriescalendar.view.activities

import android.app.Activity
import android.content.Intent
import android.database.DataSetObserver
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.databinding.SeriesCardsListRepositoryActivityBinding
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import com.milenialsatwork.seriescalendar.view.adapter.SeriesListAdapter
import com.milenialsatwork.seriescalendar.view.ui.dialog.InputDialog
import com.milenialsatwork.seriescalendar.viewmodel.factory.CardViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SeriesCardListActivity : Activity(), LifecycleOwner {

    companion object {
        private var TAG: String = "SeriesCardListRepositoryActivity"
        private val newSeriesDialogRequestCode = 1
    }

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: SeriesCardsListRepositoryActivityBinding
    private lateinit var livedataObserver: SeriesObserver
    private lateinit var lifecycleRegistry: LifecycleRegistry

    // Todo: to ViewModel Start
    private lateinit var seriesRepository: SeriesCardRepository
    private lateinit var cardListRecyclerView: RecyclerView
    private val cardViewModel by lazy {
        CardViewModelFactory(this).createFromContext()
    }
    // Todo: to ViewModel end

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SeriesCardsListRepositoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        cardListRecyclerView = findViewById(R.id.card_list_view)
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED

        seriesRepository = cardViewModel.getDataSource().getCardsRepository()
        val adapter = SeriesListAdapter(this, seriesRepository)

        cardListRecyclerView.adapter = adapter
        cardListRecyclerView.layoutManager = LinearLayoutManager(this)
        livedataObserver = SeriesObserver(cardListRecyclerView)

        binding.addFab.setOnClickListener { view ->
            SCLog.d(TAG, "addFab.setOnClickListener: clicked")
            Snackbar.make(view, "Adding new series", Snackbar.LENGTH_LONG).show()
            cardViewModel.addNewTrackedSeries(view, this) { updateList() }
        }

        cardViewModel.cardsLiveData.observe(this, {
            it?.let {
                adapter.submitList(it)
            }
        })
//        binding.addFab.setImageResource(R.mipmap.fab_icon_round)
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    override fun onDestroy() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        super.onDestroy()
    }



    private fun doUpdateList() = runBlocking {
        launch {
            SCLog.d(TAG, "doUpdateList: Call Update List")
            updateList()
        }
    }

    private fun printAllSeries() {
        for (series: Series in seriesRepository.seriesCardsList) {
            SCLog.d(TAG, "printAllSeries: $series")
        }
    }



    private fun updateList() {
        SCLog.d(TAG, "updateList")
        cardListRecyclerView.refreshDrawableState()
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

    private class SeriesObserver(private var listView: RecyclerView): DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            SCLog.d(TAG, "SeriesObserver.onChanged: updating view")
            listView.refreshDrawableState()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
