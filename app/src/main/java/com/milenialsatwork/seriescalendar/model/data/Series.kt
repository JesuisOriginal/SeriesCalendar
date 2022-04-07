package com.milenialsatwork.seriescalendar.model.data

import com.milenialsatwork.seriescalendar.R

class Series () {
    constructor(seriesName: String, lastUpdated: String, lastChapter: String, imageResourceId: Int)
            : this() {
        this.image = imageResourceId
        this.lastChapter = lastChapter
        this.lastUpdate = lastUpdate
        this.name = seriesName
    }

    var name: String = ""
        get() = field
        set(newName: String) { field = newName }

    var lastUpdate: String = ""
        get() = field
        set(newName: String) { field = newName }

    var lastChapter: String = ""
        get() = field
        set(newName: String) { field = newName }

    var image: Int = R.mipmap.vegeto
        get() = field
        set(newImageResourceId: Int) { field = newImageResourceId }

    override fun toString(): String {
        return "Series Name: $name with $lastChapter Last Chapter"
    }
}