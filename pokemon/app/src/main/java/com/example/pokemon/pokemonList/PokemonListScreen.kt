package com.example.pokemon.pokemonList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokemon.data.models.PokemonListEntry
import com.example.pokemon.data.remote.responces.PokemonList

@Composable
fun PokemonListScreen(navController: NavController,list: List<PokemonListEntry>) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id =R.drawable.),
//                contentDescription = "Pokemon" ,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(CenterHorizontally)
//            )

            SearchBar(
                hint = "Search....",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(
                modifier = Modifier.width(300.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(1)
            ) {

                items(list.count()) {
                    PokemonEntry(list[it],navController)
                }

            }

        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier =Modifier,
    hint :String ="" ,
    onSearch : (String) ->Unit ={}
){

    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplay by remember {
        mutableStateOf(hint!="")
    }
    Box(modifier = modifier ){
        BasicTextField(
            value = text ,
            onValueChange = {
                text=it
                onSearch(it)
            },
            maxLines = 1 ,
            singleLine = true ,
            textStyle = TextStyle(Color.Black),
            modifier= Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplay = !it.isFocused
                }
            )
        if (isHintDisplay){
            Text(
                text="hint",
                color = Color.LightGray ,
                modifier=Modifier.padding(horizontal = 20.dp , vertical = 12.dp)
            )
        }
    }
}
@Composable
fun PokemonEntry(
    entry:PokemonListEntry ,
    navController: NavController ,
    viewmodel :PokemonListViewModel = hiltViewModel()
){

    Box(
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(Color.White)
            .clickable {
                navController.navigate("pokemon_details_screen/${entry.pokemonName}")
            }
        ){
        Column {
            AsyncImage(
                model =entry.imageUrl ,
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
                )
            Text(
                text = entry.pokemonName ,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
                )

        }

    }
}