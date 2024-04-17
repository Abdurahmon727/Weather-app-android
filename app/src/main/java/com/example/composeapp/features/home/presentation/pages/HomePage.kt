package com.example.composeapp.features.home.presentation.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.composeapp.features.home.presentation.viewmodel.HomeViewModel
import com.example.composeapp.core.theme.ComposeAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage() {
     HomeViewModel()


    val pagerState = rememberPagerState(pageCount = {100})
    val coroutine = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) {
            SubcomposeAsyncImage(
                modifier= Modifier.fillMaxHeight(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://source.unsplash.com/random/${pagerState.currentPage+100}")
                    .crossfade(true)
                    .build(), contentDescription = "",
                loading = {
                    Box (modifier= Modifier.fillMaxSize()){
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            strokeWidth = 2.dp
                        )
                    }
                },
                contentScale = ContentScale.Crop
            )
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                coroutine.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            }) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAppTheme {
        HomePage()
    }
}