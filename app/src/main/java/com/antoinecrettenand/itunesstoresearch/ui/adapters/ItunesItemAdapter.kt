package com.antoinecrettenand.itunesstoresearch.ui.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antoinecrettenand.itunesstoresearch.R
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItem
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItemDiffCallback
import com.antoinecrettenand.itunesstoresearch.ui.viewmodels.MainViewModel
import com.bumptech.glide.Glide


class ItunesItemAdapter(
    private val context: Context,
    private val itemClickListener: OnItemClickListener
) : ListAdapter<ItunesItem, ItunesItemAdapter.ViewHolder>(ItunesItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.media_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itunesItem: ItunesItem = getItem(position)
        holder.trackName.text = itunesItem.trackName
        holder.artistName.text = itunesItem.artistName
        holder.mediaType.text = itunesItem.getReadableType()
        holder.releaseDate.text = itunesItem.getReadableReleaseDate()
        holder.shortDescription.text = itunesItem.getReadableShortDescription()

        val scaledPreviewImage = itunesItem.artworkUrl100!!.replace("100", "400")

        Glide.with(context)
            .load(scaledPreviewImage)
            .centerInside()
            .into(holder.previewImage)
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val trackName: TextView = itemView.findViewById(R.id.track_name)
        val mediaType: TextView = itemView.findViewById(R.id.media_type)
        val artistName: TextView = itemView.findViewById(R.id.artist_name)
        val releaseDate: TextView = itemView.findViewById(R.id.release_date)
        val previewImage: ImageView = itemView.findViewById(R.id.preview_image)
        val shortDescription: TextView = itemView.findViewById(R.id.description)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val itunesItem = getItem(position)
                    itemClickListener.onItemClick(itunesItem)
                }
            }
        }
    }
}

abstract class RecyclerTouchListener(private val navController: NavController) : OnItemClickListener {
    abstract fun onItemClick(navController: NavController = this.navController, itunesItem: ItunesItem)

    override fun onItemClick(itunesItem: ItunesItem) {
        return onItemClick(navController, itunesItem)
    }
}


interface OnItemClickListener {
    fun onItemClick(itunesItem: ItunesItem)
}
