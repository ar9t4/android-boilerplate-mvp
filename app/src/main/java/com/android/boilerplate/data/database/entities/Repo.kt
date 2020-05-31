package com.android.boilerplate.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Repo(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("clone_url")
    var cloneUrl: String
)