package com.konkuk.strhat.core.component.stateView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun LoadingScreen(
    loadingDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = colors.Gray200)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoadingViewAnimation(loadingDescription)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowLoadingScreen() {
    StrHatTheme {
        LoadingScreen(R.string.loading_description)
    }
}
