package hr.fer.teslasjourney.data.models

import com.google.gson.annotations.SerializedName

open class ErrorResponse(
    @SerializedName("errorCode") val errorCode: String,
    @SerializedName("errorMessage") val errorMessage: String
)