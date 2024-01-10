package com.example.pokemon.data.remote

import com.example.pokemon.data.remote.responces.Pokemon
import com.example.pokemon.data.remote.responces.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList():PokemonList


    @GET()
    suspend fun getPokemonInfo(
        @Path("name") name :String
    ):Pokemon
}