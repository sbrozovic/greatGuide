package hr.fer.teslasjourney.ui.city_location.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.data.models.Video
import hr.fer.teslasjourney.ui.shared.loadImage
import kotlinx.android.synthetic.main.video_item.view.*

class VideoAdapter constructor(var list: List<Video>, var context: Context) : RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return MyViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindViewHolder(list[position])
    }

    class MyViewHolder constructor(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        fun bindViewHolder(video: Video) {
            with(view.image){
                loadImage(video.imageUrl)
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.videoUrl))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.setPackage("com.google.android.youtube")
                    context.startActivity(intent)
                }
            }
        }
    }
}