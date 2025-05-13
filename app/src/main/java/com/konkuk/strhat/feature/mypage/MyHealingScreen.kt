package com.konkuk.strhat.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.textfield.LongTextField
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun MyHealingRoute(
    padding: PaddingValues,
    navigateToMyPage: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMyPageModel()
    }
    val myPageModel by viewModel.myPageModel.collectAsState()

    MyHealingScreen(
        padding = padding,
        healing = myPageModel.hobbyHealingStyle,
        onHealingChange = viewModel::updateHealing,
        navigateToMyPage = {
            viewModel.patchHealingInfo {
                navigateToMyPage()
            }
        }
    )
}

@Composable
private fun MyHealingScreen(
    padding: PaddingValues,
    healing: String,
    onHealingChange: (String) -> Unit,
    navigateToMyPage: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(padding),
    ) {
        Column {
            PageDescriptionSection(
                titleResId = R.string.my_account_title,
                descriptionResId = R.string.onboarding_type_description
            )
            Spacer(Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.onboarding_hobby_title),
                style = typography.title1_b_18,
                color = colors.MainBlack,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LongTextField(
                value = healing,
                onValueChange = onHealingChange,
                hint = stringResource(R.string.textfield_hobby),
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
private fun PreviewMyHealingScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        MyHealingScreen(
            padding = PaddingValues(),
            healing = "",
            onHealingChange = {},
            navigateToMyPage = {}
        )
    }
}