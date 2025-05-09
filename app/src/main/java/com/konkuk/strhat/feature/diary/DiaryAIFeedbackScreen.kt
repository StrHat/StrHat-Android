package com.konkuk.strhat.feature.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.SummaryBox
import com.konkuk.strhat.core.component.bottomsheet.ChatModeBottomSheet
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.button.UnderlineButton
import com.konkuk.strhat.core.component.dialog.StrHatDialog
import com.konkuk.strhat.core.component.section.PageDescriptionSection
import com.konkuk.strhat.core.component.section.TitleSection
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.domain.entity.DiaryFeedbackModel
import com.konkuk.strhat.domain.entity.TotalDiaryModel
import com.konkuk.strhat.feature.diary.component.DiaryAIFeedbackKeywordBox
import com.konkuk.strhat.feature.diary.component.DiaryAIFeedbackRecommendationBox
import com.konkuk.strhat.feature.diary.state.DiaryAIFeedbackState
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun DiaryAIFeedbackRoute(
    padding: PaddingValues,
    date: String,
    diaryFeedbackModel: DiaryFeedbackModel,
    navigateToChat: () -> Unit,
    navigateToTodayStressScore: () -> Unit,
    popBackStack: () -> Unit,
    navigateToMyPageChatHistory: () -> Unit,
    navController: NavController,
    viewModel: DiaryAIFeedbackViewModel = hiltViewModel(),
    addDiaryViewModel: AddDiaryViewModel = hiltViewModel()
) {
    val diaryAIFeedbackState by viewModel.diaryAIFeedbackState.collectAsState()
    val totalDiary by addDiaryViewModel.totalDiaryState.collectAsState()

    LaunchedEffect(Unit) {
        addDiaryViewModel.getTotalDiary(date)
    }

    DiaryAIFeedbackScreen(
        padding = padding,
        diaryFeedbackModel = diaryFeedbackModel,
        diaryAIFeedbackState = diaryAIFeedbackState,
        totalDiary = totalDiary,
        navigateToChat = navigateToChat,
        navigateToTodayStressScore = navigateToTodayStressScore,
        popBackStack = popBackStack,
        navigateToMyPageChatHistory = navigateToMyPageChatHistory,
        navController = navController
    )
}

@Composable
private fun DiaryAIFeedbackScreen(
    padding: PaddingValues,
    diaryFeedbackModel: DiaryFeedbackModel,
    diaryAIFeedbackState: DiaryAIFeedbackState,
    totalDiary: TotalDiaryModel,
    navigateToChat: () -> Unit,
    navigateToTodayStressScore: () -> Unit,
    popBackStack: () -> Unit,
    navigateToMyPageChatHistory: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val previousRoute = remember {
        navController.previousBackStackEntry?.destination?.route
    }

    var showTotalDiaryDialog by remember { mutableStateOf(false) }
    var isChatModeBottomSheetVisible by remember { mutableStateOf(false) }

    val today = remember {
        Clock.System.todayIn(TimeZone.currentSystemDefault())
    }

    val formattedDate = stringResource(
        id = R.string.diary_ai_feedback_date,
        today.year, today.monthNumber, today.dayOfMonth
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.MainWhite)
            .padding(top = 70.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            PageDescriptionSection(
                titleResId = R.string.diary_ai_feedback_screen_title
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TitleSection(
                    title = stringResource(
                        id = R.string.diary_ai_feedback_date_title,
                        formattedDate
                    )
                )

                Spacer(modifier = Modifier.width(6.dp))

                UnderlineButton(
                    btnText = stringResource(R.string.diary_ai_total_diary_button),
                    modifier = Modifier.noRippleClickable {
                        showTotalDiaryDialog = true
                    }
                )
            }

            SummaryBox(
                summary = diaryFeedbackModel.summary,
                backgroundColor = colors.Gray100,
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            TitleSection(
                title = stringResource(R.string.diary_ai_feedback_positive_title)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(46.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DiaryAIFeedbackKeywordBox(
                    keywords = diaryFeedbackModel.positiveKeywords,
                    feedBackBoxBackgroundColor = colors.SubBlue,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(R.drawable.ic_strhat_green_shadow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .width(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TitleSection(
                title = stringResource(R.string.diary_ai_feedback_negative_title)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(46.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_strhat_red_shadow),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .width(100.dp)
                )
                DiaryAIFeedbackKeywordBox(
                    keywords = diaryFeedbackModel.negativeKeywords,
                    feedBackBoxBackgroundColor = colors.Gray100,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TitleSection(
                title = stringResource(R.string.diary_ai_feedback_recommendation_title)
            )

            DiaryAIFeedbackRecommendationBox(
                diaryAIFeedbackRecommendation = diaryFeedbackModel.stressReliefSuggestions,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            val isDiaryRoute = previousRoute?.contains("Diary") == true

            StrHatButton(
                isDisabled = isDiaryRoute,
                text =
                    if (isDiaryRoute)
                        stringResource(R.string.diary_ai_feedback_quit_button)
                    else
                        stringResource(R.string.confirm),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .weight(1f),
                onClick = {
                    if (isDiaryRoute)
                        navigateToTodayStressScore()
                    else
                        popBackStack()
                }
            )
            StrHatButton(
                isDisabled = false,
                text =
                    if (isDiaryRoute)
                        stringResource(R.string.diary_ai_feedback_chat_button)
                    else
                        stringResource(R.string.my_page_ai_feedback_chat_history_button),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .weight(1f),
                onClick = {
                    if (isDiaryRoute)
                        isChatModeBottomSheetVisible = true
                    else
                        navigateToMyPageChatHistory()
                }
            )
        }
    }

    if (showTotalDiaryDialog) {
        Dialog(
            onDismissRequest = { showTotalDiaryDialog = false }
        ) {
            StrHatDialog(
                titleResId = R.string.diary_ai_total_diary_button,
                diary = totalDiary.content,
                onConfirmButtonClick = { showTotalDiaryDialog = false },
                onDismissButtonClick = { showTotalDiaryDialog = false }
            )
        }
    }

    if (isChatModeBottomSheetVisible) {
        ChatModeBottomSheet(
            isVisible = isChatModeBottomSheetVisible,
            onDismiss = { isChatModeBottomSheetVisible = false },
            onChatModeSelected = { selectedMode ->
                isChatModeBottomSheetVisible = false
            },
            navigateToChat = navigateToChat
        )
    }
}

@Preview
@Composable
fun DiaryAIFeedbackScreenPreview() {
    StrHatTheme {
        val diaryAIFeedbackExampleState = DiaryAIFeedbackState(
            diaryAIFeedbackSummary = stringResource(R.string.diary_ai_feedback_summary_example),
            diaryAIFeedbackPositiveKeywords = listOf("긍정1", "긍정2", "긍정3"),
            diaryAIFeedbackNegativeKeywords = listOf("부정1", "부정2", "부정3"),
            diaryAIFeedbackRecommendation = stringResource(R.string.diary_ai_feedback_recommendation_example)
        )

        DiaryAIFeedbackScreen(
            padding = PaddingValues(),
            diaryFeedbackModel = DiaryFeedbackModel("", listOf(), listOf(), "", 1),
            diaryAIFeedbackState = diaryAIFeedbackExampleState,
            totalDiary = TotalDiaryModel("", 1),
            navigateToChat = {},
            navigateToTodayStressScore = {},
            popBackStack = {},
            navigateToMyPageChatHistory = {},
            navController = rememberNavController(),
            modifier = Modifier.background(colors.MainWhite)
        )
    }
}