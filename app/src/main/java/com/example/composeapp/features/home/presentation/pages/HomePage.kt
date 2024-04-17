package com.example.composeapp.features.home.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeapp.core.composables.CustomNetworkImage
import com.example.composeapp.core.composables.useLocalNavHostController
import com.example.composeapp.core.domain.UiStatus
import com.example.composeapp.core.extensions.H
import com.example.composeapp.features.home.data.models.forecast.ForecastResponse
import com.example.composeapp.features.home.presentation.viewmodel.HomeEvent
import com.example.composeapp.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomePage(
    viewModel: HomeViewModel
) {
    val navController = useLocalNavHostController()
    val uiState = viewModel.uiState.collectAsState().value
    val event = viewModel::onEvent


    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (uiState.status) {
                UiStatus.Pure ->
                    event.invoke(HomeEvent.GetForecast)


                UiStatus.Loading ->
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        strokeWidth = 2.dp
                    )


                UiStatus.Success ->
                    SuccessContent(
                        forecast = uiState.forecast!!
                    )


                UiStatus.Failure -> {
                    Text(text = "failure")
                }
            }
        }
    }
}

@Composable
private fun SuccessContent(forecast: ForecastResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            modifier = Modifier.fillMaxSize(),
            value = "", onValueChange = {})
        CustomNetworkImage(
            Modifier.size(120.dp),
            image = "https://${forecast.current.condition.icon}"
        )
        Text(
            text = forecast.location.name,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp, fontWeight = FontWeight.W600
            ),
        )
        16.H()
        Text(
            text = "${forecast.current.tempC} \u2103",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 70.sp, fontWeight = FontWeight.W600
            ),
        )
        35.H()


    }
}