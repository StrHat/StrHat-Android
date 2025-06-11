package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.SummaryBox
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.stateView.LoadingScreen
import com.konkuk.strhat.domain.entity.StressScoreModel
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.coroutines.delay

@Composable
fun TodayStressScoreRoute(
    padding: PaddingValues,
    date: String,
    navigateToHome: () -> Unit,
    navigateToMyPage: () -> Unit,
    navController: NavController,
    viewModel: StressScoreViewModel = hiltViewModel()
) {
    val stressScoreState by viewModel.stressScoreState.collectAsState()
    var showContent by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getStressScore(date)
        delay(8000)
        showContent = true
    }

    if (!showContent) {
        LoadingScreen(
            loadingDescription = R.string.chat_feedback_loading,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        TodayStressScoreScreen(
            padding = padding,
            stressScoreState = stressScoreState,
            navigateToHome = navigateToHome,
            navigateToMyPage = navigateToMyPage,
            navController = navController
        )
    }
}

@Composable
fun TodayStressScoreScreen(
    padding: PaddingValues,
    stressScoreState: StressScoreModel,
    navigateToHome: () -> Unit,
    navigateToMyPage: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val previousRoute = remember {
        navController.previousBackStackEntry?.destination?.route
    }

    val stressScoreColor =
        when (stressScoreState.stressScore) {
            in 1 .. 5 -> colors.MainBlue
            in 9 .. 10 -> colors.MainRed
            else -> colors.Gray400
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row {
                Text(
                    text = stressScoreState.nickname,
                    style = typography.head1_b_24,
                    color = colors.MainBlue
                )
                Text(
                    text = stringResource(R.string.stress_score_nickname_description),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.stress_score_today_title),
                style = typography.head1_b_24,
                color = colors.MainBlack
            )

            Row {
                Text(
                    text = stressScoreState.stressScore.toString(),
                    style = typography.head0_b_26,
                    color = stressScoreColor,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = stringResource(R.string.stress_score_stress_score_end),
                    style = typography.head1_b_24,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = stressScoreState.level,
                    style = typography.head2_b_20,
                    color = stressScoreColor,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = stringResource(R.string.stress_score_level_end),
                    style = typography.head2_r_20,
                    color = colors.MainBlack
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.stress_score_level_description),
                style = typography.body2_r_15,
                color = colors.MainBlack
            )

            Spacer(modifier = Modifier.height(46.dp))

            PageDescriptionSection(
                titleResId = R.string.stress_score_analysis_title
            )

            Spacer(modifier = Modifier.height(16.dp))

            SummaryBox(
                summary = stressScoreState.analysis,
                backgroundColor = colors.Gray100
            )
        }

        StrHatButton(
            text =
                if (previousRoute?.contains("MyPage") == true)
                    stringResource(R.string.confirm)
                else
                    stringResource(R.string.stress_score_to_home),
            onClick = {
                when {
                    previousRoute?.contains("AIFeedback") == true -> {
                        navigateToHome()
                    }
                    previousRoute?.contains("Chat") == true -> {
                        navigateToHome()
                    }
                    previousRoute?.contains("MyPage") == true -> {
                        navigateToMyPage()
                    }
                }
            },
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Preview
@Composable
fun TodayStressScoreScreenPreview() {
    StrHatTheme {
        TodayStressScoreScreen(
            padding = PaddingValues(),
            stressScoreState = StressScoreModel("", 1, "", "", ""),
            navigateToHome = {},
            navigateToMyPage = {},
            navController = rememberNavController()
        )
    }
}