package hr.fer.teslasjourney.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CitiesModel (
    @SerializedName("name") val cityName: String,
    @SerializedName("description") val cityDescription: String,
    @SerializedName("size") val citySize: Int,
    @SerializedName("hasData") val hasData: Boolean,
    @SerializedName("id") val id: String,
    @SerializedName("position") val position: String,
    @SerializedName("country")val country: Country
) : Serializable

enum class Country(val country: String){
    @SerializedName("Hrvatska")
    CROATIA("Hrvatska"),
    @SerializedName("Mađarska")
    HUNGARY("Mađarska"),
    @SerializedName("SAD")
    USA("SAD"),
    @SerializedName("Austrija")
    AUSTRIA("Austrija"),
    @SerializedName("Francuska")
    FRANCE("Francuska"),
    @SerializedName("Srbija")
    SERBIA("Srbija"),
    @SerializedName("Češka")
    CZECH("Češka"),
    NONE("none")
}