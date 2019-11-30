package hr.fer.teslasjourney.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import hr.fer.teslasjourney.TeslasJourneyApp
import java.io.InputStream

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context?, glide: Glide?, registry: Registry?) {
        super.registerComponents(context, glide, registry)

        val factory = OkHttpUrlLoader.Factory(TeslasJourneyApp.instance.appComponent.okHttpClient())
        glide?.registry?.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }
}