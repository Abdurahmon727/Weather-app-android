package com.example.composeapp.core.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController


private val localNavHostController = compositionLocalOf<NavHostController?> { null }

@Composable
fun LocalNavHostControllerProvider(
    navHostController: NavHostController,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        value = localNavHostController provides navHostController,
        content = content,
    )
}

@Composable
fun useLocalNavHostController(): NavHostController {
    return localNavHostController.current
        ?: throw RuntimeException("Please wrap your app with LocalNavHostControllerProvider")
}