package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian


@Composable
fun HomeScreen(amphibiansUiState: AmphibiansUiState,
               retryAction: () -> Unit,
               modifier: Modifier = Modifier
) {

    when (amphibiansUiState){
        is AmphibiansUiState.Success ->{


            AmphibiansListScreen(amphibiansUiState.amphibians, modifier.fillMaxSize())}

        else -> {}
    }
}

@Composable
fun AmphibiansListScreen(amphibians: List<Amphibian>, modifier: Modifier) {

    LazyColumn(
        modifier =modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium))
    ){
        items(
            items = amphibians,
            key = { amphibian ->
                amphibian.name
            }
        ){
            amphibians ->
            AmphibianCard(amphibians = amphibians, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun AmphibianCard(amphibians: Amphibian, modifier: Modifier) {

    Card(modifier =  modifier, shape = RoundedCornerShape(8.dp)) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = stringResource(id = R.string.amphibian_title, amphibians.name, amphibians.type)
            , modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibians.img_src)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )

            Text(text = amphibians.description,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Justify,
            modifier =  Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        }
    }
}
