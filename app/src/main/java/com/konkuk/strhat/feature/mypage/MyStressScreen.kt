package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyStressRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyStressScreen(
        padding = padding,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
private fun MyStressScreen(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
) {

}

@Preview
@Composable
private fun PreviewMyStressScreen() {
    MyStressScreen(
        padding = PaddingValues(),
        navigateToMyPage = {}
    )
}