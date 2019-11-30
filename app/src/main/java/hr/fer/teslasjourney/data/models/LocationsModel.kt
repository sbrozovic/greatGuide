package hr.fer.teslasjourney.data.models

import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("type") val type: String, //museum, collage...
    @SerializedName("city") val city: String,
    @SerializedName("mainImage") val mainImage: String,
    @SerializedName("id")val id: String
)