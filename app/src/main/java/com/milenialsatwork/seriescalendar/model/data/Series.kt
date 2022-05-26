package com.milenialsatwork.seriescalendar.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.milenialsatwork.seriescalendar.R
import kotlinx.coroutines.newFixedThreadPoolContext
import java.net.URL

//@Fts4
@Entity(tableName = "series_table")
class Series () { //TODO: Create an Interface Series, and Make this Manga Implement Series
    constructor(seriesName: String, lastUpdated: String, lastChapter: String, imageResourceId: Int)
            : this() {
        this.image = imageResourceId
        this.lastChapter = lastChapter
        this.lastUpdate = lastUpdate
        this.name = seriesName
    }

    constructor(name: String) : this()

    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String = ""
        get() = field
        set(newName: String) { field = newName }

    var lastUpdate: String = ""
        get() = field
        set(newName: String) { field = newName }

    var lastChapter: String = ""
        get() = field
        set(newName: String) { field = newName }

    //@Ignore
    var image: Int = R.mipmap.vegeto // TODO: Change with getting from URL
        get() = field
        set(newImageResourceId: Int) { field = newImageResourceId }

    var imageUrl: String = "https://cloud.estacaonerd.com/wp-content/uploads/2021/03/07182315/gogeta-jpg_2.jpg"
        get() = field
        set(newImageUrl: String) { field = newImageUrl }

    override fun toString(): String {
        return "Series Name: $name; Last Chapter Released $lastChapter"
    }
}