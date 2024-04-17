package com.example.composeapp.features.home.presentation.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.composeapp.core.composables.useLocalNavHostController
import com.example.composeapp.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomePage(
    viewModel: HomeViewModel
) {
    val navController = useLocalNavHostController()
    val uiState = viewModel.uiState.collectAsState().value

//    when(uiState.status){}


}