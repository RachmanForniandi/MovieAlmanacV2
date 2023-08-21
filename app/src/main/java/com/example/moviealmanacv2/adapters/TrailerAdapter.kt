package com.example.moviealmanacv2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviealmanackotlin.models.TrailerModel
import com.example.moviealmanacv2.R
import com.example.moviealmanacv2.databinding.ItemTrailerBinding

class TrailerAdapter(var trailers: ArrayList<TrailerModel>, var clickListener: TrailerAdapter.OnAdapterListener): RecyclerView.Adapter<TrailerAdapter.TrailerHolder>() {

    private val TAG: String ="TrailerActivity"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerHolder {

        return TrailerHolder(ItemTrailerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TrailerHolder, position: Int) {
        val video = trailers[position]
        holder.titleTrailer.text = video.name

        holder.itemView.setOnClickListener {
            video.key?.let { it1 -> clickListener.onClick(it1) }
        }
    }

    class TrailerHolder (val binding:ItemTrailerBinding): RecyclerView.ViewHolder(binding.root){
        val titleTrailer = binding.txtTitleTrailerVideo
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    fun setDataTrailer(newVideos:List<TrailerModel>){
        trailers.clear()
        trailers.addAll(newVideos)
        notifyDataSetChanged()
        newVideos[0].key?.let { clickListener.onVideo(it) }
    }

    interface OnAdapterListener {
        fun onClick(key:String)
        fun onVideo(key:String)
    }
}