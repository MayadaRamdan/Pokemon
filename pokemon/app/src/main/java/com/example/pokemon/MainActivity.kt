package com.example.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokemon.data.models.PokemonListEntry
import com.example.pokemon.data.remote.responces.PokemonList
import com.example.pokemon.pokemonList.PokemonListScreen
import com.example.pokemon.pokemonList.PokemonListViewModel
import com.example.pokemon.ui.theme.PokemonTheme
import com.example.pokemon.util.Error11
import com.example.pokemon.util.Loader
import com.example.pokemon.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"  ){

                    composable( "pokemon_list_screen"){

                        val pokemonViewModel = hiltViewModel<PokemonListViewModel>()

                        val pokemons=pokemonViewModel.pokemonList.collectAsState()

                        when(pokemons.value){
                            is Resource.Loading<*> ->{
                                Loader()
                            }
                            is Resource.Success<*> ->{

                                val response = pokemons.value.data?.results as List<PokemonListEntry>
                                PokemonListScreen( navController,response )
                            }
                            is Resource.Error<*> -> {
                                val error = (pokemons.value as Resource.Error<*>)
                                Error11(error.toString())

                            }
                        }


                    }

                    composable(
                        "pokemon_details_screen/{pokemonNAme}",
                        arguments = listOf(

                            navArgument("pokemonNAme"){
                                type= NavType.StringType
                            }
                        )
                    ){

//                        val dominantColor= remember {
//                            val color = it.arguments?.getInt("dominantColor")
//                            color?.let { Color(it) } ?: Color.White
//                        }

                        val pokemonName = remember {
                            it.arguments?.getString("pokemonNAme")
                        }
                    }


                }

            }
        }
    }
}
