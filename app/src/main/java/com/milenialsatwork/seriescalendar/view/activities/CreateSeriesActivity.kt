package com.milenialsatwork.seriescalendar.view.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.databinding.ActivityCreateSeriesBinding
import com.milenialsatwork.seriescalendar.databinding.CardActivityBinding
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.model.utils.SCLog
import com.milenialsatwork.seriescalendar.viewmodel.card.CardViewModel
import com.milenialsatwork.seriescalendar.viewmodel.card.CreateSeriesViewModel
import com.milenialsatwork.seriescalendar.viewmodel.factory.CardViewModelFactory
import com.milenialsatwork.seriescalendar.viewmodel.factory.CreateSeriesViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateSeriesActivity(

) : AppCompatActivity() {
    private val createSeriesViewModel by viewModels<CreateSeriesViewModel> {
        CreateSeriesViewModelFactory(this)
    }

    companion object {
        private var TAG: String = "SeriesCardListRepositoryActivity"
    }

    private lateinit var binding: ActivityCreateSeriesBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addSeriesFab.setOnClickListener { view ->
            SCLog.d(TAG, "addFab.setOnClickListener: clicked")
            Snackbar.make(view, "Adding new series", Snackbar.LENGTH_LONG).show()
            createSeriesViewModel.getSeriesFromView(view)
            finishActivity(0)
        }
    }
}