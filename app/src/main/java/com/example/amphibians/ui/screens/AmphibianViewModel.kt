package com.example.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.model.Amphibian


sealed interface AmphibiansUIState  {
    data class Success(val amphibians : List<Amphibian>) : AmphibiansUIState

    object Error : AmphibiansUIState

    object Loading : AmphibiansUIState
}

class AmphibianViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel(){
    
}