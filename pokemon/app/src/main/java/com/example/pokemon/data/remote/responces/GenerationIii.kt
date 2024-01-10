package com.example.pokemon.data.remote.responces


import com.google.gson.annotations.SerializedName

data class GenerationIii(
    @SerializedName("emerald")
    val emerald: Emerald? = null,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen? = null,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphire? = null
)