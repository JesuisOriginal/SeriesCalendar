package com.milenialsatwork.seriescalendar.view.adapter

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.data.Series
import com.milenialsatwork.seriescalendar.model.repository.SeriesCardRepository
import com.milenialsatwork.seriescalendar.view.holder.CardViewHolder


class SeriesListAdapter (private val click: (Series) -> Unit) :
    ListAdapter<Series, SeriesListAdapter.SeriesViewHolder>(SeriesListAdapter),
    android.widget.ListAdapter {

    /**
     * @Description Class responsible for calculating the difference between two lists, and returning us
     * a updated list for exhibition, the ItemCallback will calculate the difference between two
     * items in the list using, so we implement how this will happen
     */
    private companion object : DiffUtil.ItemCallback<Series>() {

        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.name == newItem.name && oldItem.lastChapter == newItem.lastChapter
                    && oldItem.image == newItem.image && oldItem.lastUpdate == newItem.lastUpdate
        }
    }

    override fun getItem(index: Int): Series {
        return seriesListRep.getSerieByIndex(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(getItem(position), click)
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

        fun bind(data: Series, click: (Series) -> Unit) {
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
                val view = layoutInflater.inflate(com.milenialsatwork.seriescalendar.R.layout.base_card, parent, false)

                return SeriesViewHolder(view)
            }
        }
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(p0: Int): Boolean {
        TODO("Not yet implemented")
    }
}