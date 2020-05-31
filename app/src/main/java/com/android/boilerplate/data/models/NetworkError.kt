package com.android.boilerplate.data.models

import com.google.gson.annotations.SerializedName

data class NetworkError(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("errors")
    val errors: Any
)