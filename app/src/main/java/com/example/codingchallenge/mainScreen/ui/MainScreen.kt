package com.example.codingchallenge.mainScreen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.codingchallenge.mainScreen.data.models.CharacterResponseItem
import com.example.codingchallenge.ui.theme.CodingChallengeTheme


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    CodingChallengeTheme {
        //MainScreen()
    }
}


@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel) {

    LaunchedEffect(Unit, block= {
        mainScreenViewModel.getCharacters()
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Home(mainScreenViewModel)
    }
}

@Composable
fun Home(mainScreenViewModel: MainScreenViewModel) {
    Column() {
        Header(mainScreenViewModel)
        ListCharacters(mainScreenViewModel)
    }
}

@Composable
fun ListCharacters(mainScreenViewModel: MainScreenViewModel) {
    Column {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(mainScreenViewModel.characterList){ character ->
                CharacterItem(character)
            }
        }

        ButtonCallAPI(Modifier.align(Alignment.CenterHorizontally), mainScreenViewModel)
    }
}

@Composable
fun ButtonCallAPI(modifier: Modifier, mainScreenViewModel: MainScreenViewModel) {
    Button(onClick = { mainScreenViewModel.getCharacter() }, modifier = modifier) {
        Text(text = "CALL API")
    }
}

@Composable
fun CharacterItem(character: CharacterResponseItem) {
    Card(Modifier.fillMaxWidth(), elevation = 25.dp, shape = MaterialTheme.shapes.small) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            /*Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Character image"
            ) */
            AsyncImage(
                model = character.image,
                contentDescription = "Character image"
            )
            Text(text = character.character, fontSize = 28.sp, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun Header(mainScreenViewModel: MainScreenViewModel) {
    val character: String by mainScreenViewModel.character.observeAsState(initial = "")
    TextField(
        value = character,
        onValueChange = { mainScreenViewModel.onCharacterChange(it) },
        placeholder = {
            Text(
                text = "Enter your favorite character"
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
    )
}
