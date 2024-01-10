package com.example.pokemon.data.remote.responces


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue? = null,
    @SerializedName("yellow")
    val yellow: Yellow? = null
)