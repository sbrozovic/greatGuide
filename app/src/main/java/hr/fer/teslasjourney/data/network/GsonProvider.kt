package hr.fer.teslasjourney.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonProvider {
    private val gson: Gson = GsonBuilder()
        .create()

    fun provide(): Gson {
        return gson
    }
}