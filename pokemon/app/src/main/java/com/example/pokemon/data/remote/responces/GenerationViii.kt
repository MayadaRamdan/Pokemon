package com.example.pokemon.data.remote.responces


import com.google.gson.annotations.SerializedName

data class GenerationViii(
    @SerializedName("icons")
    val icons: Icons? = Icons()
)