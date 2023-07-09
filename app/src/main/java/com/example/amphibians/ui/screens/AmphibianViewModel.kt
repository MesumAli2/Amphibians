package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface AmphibiansUIState  {
    data class Success(val amphibians : List<Amphibian>) : AmphibiansUIState

    object Error : AmphibiansUIState

    object Loading : AmphibiansUIState
}

class AmphibianViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel()
{
    var amphibiansUIState : AmphibiansUIState by mutableStateOf(AmphibiansUIState.Loading)
        private set

    init {

        getAmphibians()
    }

     fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUIState = AmphibiansUIState.Loading
            amphibiansUIState = try {
                AmphibiansUIState.Success(amphibiansRepository.getAmphibians())
            }catch ( e : IOException){
                AmphibiansUIState.Error
            }catch (e : HttpException){
                AmphibiansUIState.Error
            }
        }
    }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibianViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}