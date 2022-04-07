package com.milenialsatwork.seriescalendar.view.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.databinding.CardActivityBinding
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.view.adapter.CardAdapter
import com.milenialsatwork.seriescalendar.viewmodel.card.CardViewModel
import com.milenialsatwork.seriescalendar.viewmodel.factory.CardViewModelFactory
import kotlin.random.Random

class CardActivity (
    ): AppCompatActivity() {
    private val cardViewModel by viewModels<CardViewModel> {
        CardViewModelFactory(this)
    }

    // TODO: Review Color Palette for dark mode (way too dark, hard to read)

    private lateinit var binding: CardActivityBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_activity)

        binding = CardActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        //        val seriesImage: ImageView = findViewById(R.id.series_image_id)
//        val seriesName: MaterialTextView = findViewById(R.id.series_name_text_field)
//        val lastChapter: MaterialTextView = findViewById(R.id.latest_chapter_text)
//        val uploadedIn: MaterialTextView = findViewById(R.id.last_uploaded_in_text)

        var currentSeriesId: Long? = null


        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentSeriesId = Random.nextLong()
        }

//        currentSeriesId?.let {
//            val currentSeriesCard = cardDetailViewModel
//        }

        val list: List<Series> = listOf(
            Series(
                "New Series",
                "Yesterday",
                "28",
                R.mipmap.vegeto
            )
        )

        val cardAdapter = CardAdapter(list)

        val recyclerView: RecyclerView = findViewById(R.id.card_list_view)
        recyclerView.adapter = cardAdapter

        cardViewModel

        binding.addFab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        binding.addFab.setImageResource(R.mipmap.fab_icon_round)
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}