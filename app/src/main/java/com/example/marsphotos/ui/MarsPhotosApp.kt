package com.example.marsphotos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsPhotosApp(model: MarsViewModel = viewModel()) {
    Scaffold(topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }) { padding ->
        HomeScreen(
            state = model.uiState,
            onRetry = model::getMarsPhotos,
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
private fun HomeScreen(state: MarsUiState, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (state) {
                MarsUiState.Loading -> {
                    CircularProgressIndicator()
                    Text(stringResource(R.string.loading))
                }
                is MarsUiState.Success -> Text(
                    pluralStringResource(R.plurals.photo_count, state.photoCount, state.photoCount),
                    textAlign = TextAlign.Center,
                )
                MarsUiState.Error -> {
                    Text(stringResource(R.string.load_error), textAlign = TextAlign.Center)
                    Button(onClick = onRetry) { Text(stringResource(R.string.retry)) }
                }
            }
        }
    }
}
