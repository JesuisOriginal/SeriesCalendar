package com.milenialsatwork.seriescalendar.model.data

import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.data.NotaDAO
import java.util.*

class NotaDAO {
    fun todos(): List<Series> {
        return notes.clone() as List<Series>
    }

    fun insert(vararg notes: Series?) {
        Companion.notes.addAll(listOf(*notes))
    }

    fun altera(position: Int, card: Series?) {
        notes[position] = card
    }

    fun remove(position: Int) {
        notes.removeAt(position)
    }

    fun swap(starterPosition: Int, finalPosition: Int) {
        Collections.swap(notes, starterPosition, finalPosition)
    }

    fun removeTodos() {
        notes.clear()
    }

    companion object {
        private val notes = ArrayList<Series?>()
    }
}