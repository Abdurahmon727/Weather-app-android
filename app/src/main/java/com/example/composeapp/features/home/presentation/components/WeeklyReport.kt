package com.example.composeapp.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeapp.core.extensions.H
import com.example.composeapp.features.home.data.models.forecast.Forecastday


@Composable
fun WeeklyReport(days: List<Forecastday>, onClick: (index: Int) -> Unit) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Weekly report", modifier = Modifier.padding(start = 16.dp))
        6.H()
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(days) { index, item ->
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp)).clickable {
                            onClick.invoke(index)
                        }
                        .background(Color.Green)
                        .padding(vertical = 16.dp, horizontal = 6.dp)
                        ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${item.day.avgtempC} \u2103",
                    )
                    AsyncImage(
                        modifier = Modifier.size(50.dp),
                        model = "https:${item.day.condition.icon}",
                        contentDescription = ""
                    )
                    Text(text = item.date)
                }
            }
        }
    }
}