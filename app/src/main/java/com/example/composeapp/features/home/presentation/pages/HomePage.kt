package com.example.composeapp.features.home.presentation.pages

import HourlyReport
import PullToRefresh
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.core.composables.CustomNetworkImage
import com.example.composeapp.core.domain.UiStatus
import com.example.composeapp.core.extensions.H
import com.example.composeapp.core.extensions.W
import com.example.composeapp.features.home.data.models.forecast.ForecastResponse
import com.example.composeapp.features.home.presentation.components.WeeklyReport
import com.example.composeapp.features.home.presentation.viewmodel.HomeEvent
import com.example.composeapp.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomePage(
    viewModel: HomeViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value
    val event = viewModel::onEvent


    Scaffold {
        PullToRefresh(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            isRefreshing = uiState.status == UiStatus.Loading,
            onRefresh = { event.invoke(HomeEvent.GetForecast) }
        ) {
            when (uiState.status) {
                UiStatus.Pure ->
                    event.invoke(HomeEvent.GetForecast)

                UiStatus.Loading ->
                    Box(
                        Modifier
                            .height(LocalConfiguration.current.screenHeightDp.dp)
                            .width(LocalConfiguration.current.screenWidthDp.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            strokeWidth = 2.dp
                        )
                    }

                UiStatus.Success -> SuccessContent(
                    forecast = uiState.forecast!!
                )

                UiStatus.Failure ->
                    Box(
                        Modifier
                            .height(LocalConfiguration.current.screenHeightDp.dp)
                            .width(LocalConfiguration.current.screenWidthDp.dp)
                    ) {
                        Text(
                            text = "Something went wrong, pull to refresh",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
            }
        }
    }
}

@Composable
private fun SuccessContent(forecast: ForecastResponse) {
    val selectedDayIndex = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            value = "", onValueChange = {},
        )
        CustomNetworkImage(
            Modifier.size(120.dp),
            image = "https:${forecast.current.condition.icon}"
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = forecast.location.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 30.sp, fontWeight = FontWeight.W600
                ),
            )
            10.W()
            Icon(painter = painterResource(id = R.drawable.location), contentDescription = "")
        }
        6.H()
        Text(
            text = "${forecast.current.tempC} \u2103",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 70.sp, fontWeight = FontWeight.W600
            ),
        )
        6.H()
        HourlyReport(
            title = "Today Hourly Report",
            hours = forecast.forecast.forecastday.first().hour
        )
        6.H()
        WeeklyReport(
            days = forecast.forecast.forecastday,
            onClick = { index ->
                selectedDayIndex.intValue = index
            },
        )
        6.H()
        key(selectedDayIndex.intValue) {
            HourlyReport(
                title = "${forecast.forecast.forecastday[selectedDayIndex.intValue].date} Hourly Report",
                hours = forecast.forecast.forecastday[selectedDayIndex.intValue].hour
            )
        }
        10.H()
    }
}