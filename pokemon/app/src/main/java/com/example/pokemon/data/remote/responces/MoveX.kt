package com.example.pokemon.data.remote.responces


import com.google.gson.annotations.SerializedName

data class MoveX(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)