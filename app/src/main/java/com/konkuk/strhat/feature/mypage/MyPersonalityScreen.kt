package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyPersonalityRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyPersonalityScreen(
        padding = padding,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
private fun MyPersonalityScreen(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
) {

}

@Preview
@Composable
private fun PreviewMyPersonalityScreen() {
    MyPersonalityScreen(
        padding = PaddingValues(),
        navigateToMyPage = {}
    )
}