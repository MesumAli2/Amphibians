package com.example.amphibians

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.AmphibianViewModel
import com.example.amphibians.ui.screens.AmphibiansUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianApp(){
    Scaffold(
        modifier =  Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Amphibians",
                        style = MaterialTheme.typography.headlineMedium)

                }
            )
        }

    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            val amphibiansViewModel: AmphibianViewModel =
                viewModel(factory = AmphibianViewModel.factory)
            HomeScreen(
                amphiansUiState = amphibiansViewModel.amphibiansUIState,
                retryAction = amphibiansViewModel::getAmphibians
            )
        }
    }
}


