package hr.fer.teslasjourney.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Chapter(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageList") val imageList: List<String>?, //here we should get null or list of images in the form of urls.
    @SerializedName("videoList") val videoList: List<Video>?, //null or videoList
    @SerializedName("individualImage") val individualImage: String?, //null or image url
    @SerializedName("location") val location: String,
    @SerializedName("position") val position: String
)

data class CityAndLocationId(
    val cityId: String,
    val locationId: String
) : Serializable

data class Video(
    @SerializedName("img") val imageUrl: String,
    @SerializedName("url") val videoUrl: String
)