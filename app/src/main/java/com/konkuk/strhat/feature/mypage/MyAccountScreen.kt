package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable

@Composable
fun MyAccountRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    MyAccountScreen(
        padding = padding,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
private fun MyAccountScreen(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.my_title),
            modifier = Modifier.noRippleClickable(navigateToMyPage)
        )
    }

}

@Preview
@Composable
private fun PreviewMyAccountScreen() {
    MyAccountScreen(
        padding = PaddingValues(),
        navigateToMyPage = {}
    )
}