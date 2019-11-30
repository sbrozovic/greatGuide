package hr.fer.teslasjourney.ui.city_location.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.ui.shared.loadImage
import kotlinx.android.synthetic.main.image_item.view.*

class ImageAdapter constructor(var list: List<String>, var context: Context) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return MyViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindViewHolder(list[position])
    }

    class MyViewHolder constructor(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {
        fun bindViewHolder(url: String) {
            view.image.loadImage(url)
        }
    }
}