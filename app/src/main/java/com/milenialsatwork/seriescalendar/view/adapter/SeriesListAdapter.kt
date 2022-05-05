package com.milenialsatwork.seriescalendar.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.model.utils.SCLog


class SeriesListAdapter (
    private val context: Context,
    private val seriesRepository: SeriesCardRepository
        ) :
    ListAdapter<Series, SeriesListAdapter.SeriesViewHolder>(DiffCallbak()) {

    companion object {
        private final var TAG = "SeriesListAdapter"
    }

    /**
     * @Description Class responsible for calculating the difference between two lists, and returning us
     * a updated list for exhibition, the ItemCallback will calculate the difference between two
     * items in the list using, so we implement how this will happen
     */
    private class DiffCallbak: DiffUtil.ItemCallback<Series>() {

        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name && oldItem.lastChapter == newItem.lastChapter
                    && oldItem.image == newItem.image && oldItem.lastUpdate == newItem.lastUpdate
        }

    }

    override fun getItem(index: Int): Series? {
        return seriesRepository.getSerieByIndex(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.base_card, parent, false)
        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        SCLog.d(TAG, "onBindViewHolder: repSize is ${seriesRepository.size}, bindIndex is $position")
        seriesRepository.getSerieByIndex(position).let {
            if (it != null) {
                holder.bind(it)
                SCLog.d(TAG, "onBindViewHolder: non null series binded $it")
            }
        }
    }

    class SeriesViewHolder(
        private var view: View
    ) : RecyclerView.ViewHolder(view) {
        private val seriesImage: ImageView = view.findViewById(R.id.series_image_id)
        private val seriesName: MaterialTextView = view.findViewById(R.id.series_name_text_field)
        private var lastChapter: MaterialTextView = view.findViewById(R.id.latest_chapter_text)
        private val uploadedIn: MaterialTextView = view.findViewById(R.id.last_uploaded_in_text)
//        private val positiveButton: MaterialButton = view.findViewById(R.id.positive)
        // TODO: Learn what the heck was this button XD

        /**
         * @Description: Should be used to allow an onClick in the Series Item View
         */
        fun bind(data: Series, click: (Series) -> Unit) {
            with(view) {
//                positiveButton.setOnClickListener { click.invoke(data) }
                seriesName.text = data.name
                lastChapter.text = data.lastChapter
                uploadedIn.text = data.lastUpdate
                seriesImage.setImageResource(data.image)
            }
        }

        fun bind(data: Series) {
            with(view) {
//                positiveButton.setOnClickListener { click.invoke(data) }
                seriesName.text = data.name
                lastChapter.text = data.lastChapter
                uploadedIn.text = data.lastUpdate
                seriesImage.setImageResource(data.image)
            }
        }

        companion object {
            fun from(parent: ViewGroup): SeriesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.base_card, parent, false)

                return SeriesViewHolder(view)
            }
        }
    }
}