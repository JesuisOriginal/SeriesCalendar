package com.milenialsatwork.seriescalendar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.view.holder.CardViewHolder

class CardAdapter (
    private val cards: List<Series>?
        ) : RecyclerView.Adapter<CardViewHolder>() {
    constructor() : this(null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.base_card, parent, false)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.lastChapter.text = cards?.get(position)?.lastChapter
        holder.seriesName.text = cards?.get(position)?.name
        holder.uploadedIn.text = cards?.get(position)?.lastUpdate
        if (cards?.get(position) != null) {
            holder.seriesImage.setImageResource(cards[position].image)
        } else {
            holder.seriesImage.setImageResource(R.mipmap.vegeto)
        }
    }

    override fun getItemCount(): Int = cards?.size ?: 0
}