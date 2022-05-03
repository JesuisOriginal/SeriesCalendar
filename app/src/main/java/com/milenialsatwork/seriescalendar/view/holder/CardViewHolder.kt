package com.milenialsatwork.seriescalendar.view.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series

class CardViewHolder(
    private var cardView: View
) : RecyclerView.ViewHolder(cardView) {
    val seriesImage: ImageView
    val seriesName: MaterialTextView
    var lastChapter: MaterialTextView
    val uploadedIn: MaterialTextView
    private val positiveButton: MaterialButton
    init {
        seriesImage = cardView.findViewById(R.id.series_image_id)
        seriesName = cardView.findViewById(R.id.series_name_text_field)
        lastChapter = cardView.findViewById(R.id.latest_chapter_text)
        uploadedIn = cardView.findViewById(R.id.last_uploaded_in_text)
        positiveButton = cardView.findViewById(R.id.positive)
    }

    fun bind(data: Series, click: (Series) -> Unit) {
        with(cardView) {
            positiveButton.setOnClickListener { click.invoke(data) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): CardViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.base_card, parent, false)

            return CardViewHolder(view)
        }
    }
}