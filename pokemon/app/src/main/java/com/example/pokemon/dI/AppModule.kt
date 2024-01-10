package com.example.pokemon.dI

import androidx.compose.foundation.text.selection.SelectionContainer
import com.example.pokemon.data.remote.PokemonApi
import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.util.Constants.Bas_Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api:PokemonApi
    )=PokemonRepository(api)

    @Singleton
    @Provides
    fun providePokemonApi() :PokemonApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Bas_Url)
            .build()
            .create(PokemonApi::class.java)

    }


}