package com.example.composeapp.core.extensions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Int.H() {
    return Spacer(modifier = Modifier.height(this.dp))
}

@Composable
fun Int.W() {
    return Spacer(modifier = Modifier.width(this.dp))
}