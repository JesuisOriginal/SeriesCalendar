package com.milenialsatwork.seriescalendar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository

class SeriesAdapter (
    private val seriesRepo: SeriesCardRepository
        ) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.base_card, parent, false)

        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        seriesRepo.getSerieByIndex(position).let {
            if (it != null) holder.bind(it)
        }
    }

    override fun getItemCount(): Int = seriesRepo.size

    class SeriesViewHolder(
        private var view: View
    ) : RecyclerView.ViewHolder(view) {
        private val seriesImage: ImageView = view.findViewById(R.id.series_image_id)
        private val seriesName: MaterialTextView = view.findViewById(R.id.series_name_text_field)
        private var lastChapter: MaterialTextView = view.findViewById(R.id.latest_chapter_text)
        private val uploadedIn: MaterialTextView = view.findViewById(R.id.last_uploaded_in_text)
        // TODO: Learn what the heck was this button XD

        fun bind(data: Series, click: (Series) -> Unit) {
            seriesName.text = data.name
            lastChapter.text = data.lastChapter
            uploadedIn.text = data.lastUpdate
            seriesImage.setImageResource(data.image)
        }

        fun bind(data: Series) {
            with(view) {
                seriesName.text = data.name
                lastChapter.text = data.lastChapter
                uploadedIn.text = data.lastUpdate
                seriesImage.setImageResource(data.image)
            }
        }

        companion object {
            fun from(parent: ViewGroup?): SeriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent?.context)
                val view = layoutInflater.inflate(R.layout.base_card, parent, false)

                return SeriesViewHolder(view)
            }
        }
    }

    object DiffUtilCallback: DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name
        }
    }
}