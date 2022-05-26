package com.milenialsatwork.seriescalendar.model.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.milenialsatwork.seriescalendar.utils.TestUtils
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var seriesDao: SeriesDao
    private lateinit var db: SeriesDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SeriesDatabase::class.java).build()
        seriesDao = db.seriesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

//    @Ignore("Database Not Fully Implemented")
//    @Test
//    @Throws(Exception::class)
//    fun writeUserAndReadInList() {
//        val series: Series = TestUtils.createSeries().also {
//            it.name = "TestDBSeries"
//        }
//        seriesDao.insertAll(series)
////        val byName = seriesDao.fetchSeries()..get(seriesDao.fetchSeries().indexOf(series))
////        assertThat(byName, equalTo(series))
//    }

}