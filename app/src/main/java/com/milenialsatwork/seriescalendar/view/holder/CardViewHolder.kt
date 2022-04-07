package com.milenialsatwork.seriescalendar.view.holder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.milenialsatwork.seriescalendar.R

class CardViewHolder(
    cardView: View
) : RecyclerView.ViewHolder(cardView) {
    val seriesImage: ImageView
    val seriesName: MaterialTextView
    var lastChapter: MaterialTextView
    val uploadedIn: MaterialTextView
    init {
        seriesImage = cardView.findViewById(R.id.series_image_id)
        seriesName = cardView.findViewById(R.id.series_name_text_field)
        lastChapter = cardView.findViewById(R.id.latest_chapter_text)
        uploadedIn = cardView.findViewById(R.id.last_uploaded_in_text)
    }
}