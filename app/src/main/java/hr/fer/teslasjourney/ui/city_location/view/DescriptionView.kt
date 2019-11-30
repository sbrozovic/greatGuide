package hr.fer.teslasjourney.ui.city_location.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import hr.fer.teslasjourney.R
import kotlinx.android.synthetic.main.view_description.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.teslasjourney.data.models.Video
import hr.fer.teslasjourney.ui.shared.loadImage

class DescriptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_description, this)
    }

    fun setTitle(text: String) {
        chapterName.text = text
    }

    fun setDescription(text: String) {
        descriptionLabel.text = text
    }

    fun setIndividualImage(url: String){
        individualImage.visibility = View.VISIBLE
        individualImage.loadImage(url)
    }

    fun setImages(list: List<String>) {
        imagesRecyclerView.visibility = View.VISIBLE
        imagesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        imagesRecyclerView.adapter = ImageAdapter(list, context)
    }

    fun setVideos(list: List<Video>){
        videosRecyclerView.visibility = View.VISIBLE
        videosRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        videosRecyclerView.adapter = VideoAdapter(list, context)
    }
}