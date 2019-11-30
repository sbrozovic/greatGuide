package hr.fer.teslasjourney.ui.shared

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import hr.fer.teslasjourney.di.GlideApp

fun ImageView.loadImage(url: String?, @DrawableRes placeholder: Int = 0) {
    loadImage(url, placeholder, placeholder)
}

fun ImageView.loadImage(url: String?,
                        @DrawableRes placeholder: Int = 0,
                        @DrawableRes errorPlaceholder: Int = 0,
                        onLoadListener: (() -> Unit)? = null) {
    loadImage(url, placeholder, errorPlaceholder, onLoadListener, onLoadListener)
}

fun ImageView.loadImage(url: String?,
                        @DrawableRes placeholder: Int = 0,
                        @DrawableRes errorPlaceholder: Int = 0,
                        successListener: (() -> Unit)? = null,
                        failListener: (() -> Unit)? = null) {
    GlideApp.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(errorPlaceholder)
        .fallback(errorPlaceholder)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?,
                                      model: Any?,
                                      target: Target<Drawable>?,
                                      isFirstResource: Boolean): Boolean {
                failListener?.invoke()
                return false
            }

            override fun onResourceReady(resource: Drawable?,
                                         model: Any?,
                                         target: Target<Drawable>?,
                                         dataSource: DataSource?,
                                         isFirstResource: Boolean): Boolean {
                successListener?.invoke()
                return false
            }
        })
        .into(this)
}