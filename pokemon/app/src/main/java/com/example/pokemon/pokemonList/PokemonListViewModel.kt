package com.example.pokemon.pokemonList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.models.PokemonListEntry
import com.example.pokemon.data.remote.responces.PokemonList
import com.example.pokemon.repository.PokemonRepository
import com.example.pokemon.util.Error11
import com.example.pokemon.util.Loader
import com.example.pokemon.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository :PokemonRepository
):ViewModel(){

    var loadError = mutableStateOf("")
    var isloading = mutableStateOf(false)
    var endReach = mutableStateOf(false)

    private val _pokemonList : MutableStateFlow<Resource<PokemonList>>
            = MutableStateFlow(Resource.Loading())

    val pokemonList : StateFlow<Resource<PokemonList>> =_pokemonList

    fun loadPokemon (){
        viewModelScope.launch {
            isloading.value =true
            val result = repository.getPokemonList()
            _pokemonList.value= result
        }
    }


}