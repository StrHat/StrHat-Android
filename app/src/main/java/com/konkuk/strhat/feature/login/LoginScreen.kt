package com.konkuk.strhat.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun LoginRoute(
    padding: PaddingValues,
    navigateToOnBoarding: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val loginResult = viewModel.loginResult.collectAsStateWithLifecycle()

    LaunchedEffect(loginResult.value) {
        if (loginResult.value?.isSuccess == true) {
            if (loginResult.value?.isExistingUser == true) {
                navigateToHome()
            } else {
                navigateToOnBoarding()
            }
        }
    }

    LoginScreen(
        padding = padding,
        onButtonClick = { viewModel.loginWithKakao(context = context) }
    )
}

@Composable
fun LoginScreen(
    padding: PaddingValues,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo_blue),
            contentDescription = stringResource(R.string.app_name_korean),
            tint = colors.MainBlue
        )
        Text(
            text = stringResource(R.string.app_name_korean),
            color = colors.MainBlue,
            style = typography.head1_b_24,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = stringResource(R.string.login_social_login),
            color = colors.Gray500,
            style = typography.body1_m_16,
            modifier = Modifier.padding(top = 135.dp)
        )
        HorizontalDivider(color = colors.Gray500, modifier = Modifier.padding(20.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .background(color = colors.SubYellow, shape = RoundedCornerShape(8.dp))
                .noRippleClickable(onButtonClick)
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_kakao),
                contentDescription = stringResource(R.string.app_name_korean),
                tint = colors.MainBlack
            )

            Text(
                text = stringResource(R.string.login_kakao_login),
                color = colors.MainBlack,
                style = typography.body1_b_16,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        LoginScreen(
            padding = PaddingValues(),
            onButtonClick = {}
        )
    }
}