package com.example.pokemon.data.remote.responces


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: MoveX? = MoveX(),
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>? = listOf()
)