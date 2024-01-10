package com.example.pokemon.repository

import com.example.pokemon.data.remote.PokemonApi
import com.example.pokemon.data.remote.responces.Pokemon
import com.example.pokemon.data.remote.responces.PokemonList
import com.example.pokemon.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api :PokemonApi
) {

     suspend fun getPokemonList() :Resource<PokemonList>{
         val response = try{
             api.getPokemonList()
         }catch (e :Exception){
             return Resource.Error("unknown error ")
         }
         return Resource.Success(response)
     }

    //======================================================================


    suspend fun getPokemonInfo( PokemonName :String) :Resource<Pokemon>{
        val response = try{
            api.getPokemonInfo(PokemonName)
        }catch (e :Exception){
            return Resource.Error("unknown error ")
        }
        return Resource.Success(response)
    }

}