package com.example.composeapp.core.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun CustomNetworkImage(
    modifier: Modifier = Modifier,
    image: String,
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current).data(
            image
        ).crossfade(true).build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}