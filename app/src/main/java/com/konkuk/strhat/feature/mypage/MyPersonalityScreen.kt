package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.textfield.LongTextField
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MyPersonalityRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMyPageModel()
    }
    val myPageModel by viewModel.myPageModel.collectAsState()

    MyPersonalityScreen(
        padding = padding,
        personality = myPageModel.personality,
        onPersonalityChange = viewModel::updatePersonality,
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
private fun MyPersonalityScreen(
    padding: PaddingValues,
    personality: String,
    onPersonalityChange: (String) -> Unit,
    navigateToMyPage: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        Column {
            Text(
                text = stringResource(R.string.my_account_title),
                style = typography.head1_b_24,
                color = colors.MainBlack,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = stringResource(R.string.onboarding_type_description),
                style = typography.body3_r_14,
                color = colors.MainBlack,
                modifier = Modifier.padding(bottom = 30.dp)
            )

            Text(
                text = stringResource(R.string.onboarding_personality_title),
                style = typography.title1_b_18,
                color = colors.MainBlack,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LongTextField(
                value = personality,
                onValueChange = onPersonalityChange,
                hint = stringResource(R.string.textfield_personality_type),
                maxLength = 1000,
                modifier = Modifier.fillMaxWidth()
            )
        }

        StrHatButton(
            isDisabled = false,
            text = stringResource(R.string.confirm),
            onClick = navigateToMyPage,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
private fun PreviewMyPersonalityScreen() {
    MyPersonalityScreen(
        padding = PaddingValues(),
        personality = "",
        onPersonalityChange = {},
        navigateToMyPage = {}
    )
}