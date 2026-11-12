package com.example.marsphotos.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.IOException
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import com.example.marsphotos.network.MarsApi

sealed interface MarsUiState {
    data object Loading : MarsUiState
    data class Success(val photoCount: Int) : MarsUiState
    data object Error : MarsUiState
}

class MarsViewModel : ViewModel() {
    var uiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        uiState = MarsUiState.Loading
        viewModelScope.launch {
            uiState = try {
                MarsUiState.Success(MarsApi.service.getPhotos().size)
            } catch (_: IOException) {
                MarsUiState.Error
            } catch (_: HttpException) {
                MarsUiState.Error
            } catch (_: SerializationException) {
                MarsUiState.Error
            }
        }
    }
}
