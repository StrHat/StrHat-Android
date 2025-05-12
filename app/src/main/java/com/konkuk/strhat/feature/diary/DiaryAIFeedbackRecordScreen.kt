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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import kotlinx.datetime.LocalDate

@Composable
fun DiaryAIFeedbackRecordRoute(
    padding: PaddingValues,
    date: String,
    navigateToChat: (Int) -> Unit,
    navigateToTodayStressScore: (String) -> Unit,
    popBackStack: () -> Unit,
    navigateToMyPageChatHistory: () -> Unit,
    navController: NavController,
    viewModel: DiaryViewModel = hiltViewModel(),
    addDiaryViewModel: AddDiaryViewModel = hiltViewModel()
) {
    val diaryFeedbackState by viewModel.diaryFeedbackState.collectAsState()
    val totalDiary by addDiaryViewModel.totalDiaryState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDiaryFeedback(date)
        addDiaryViewModel.getTotalDiary(date)
    }

    DiaryAIFeedbackRecordScreen(
        padding = padding,
        date = date,
        diaryFeedbackState = diaryFeedbackState,
        totalDiary = totalDiary,
        navigateToChat = navigateToChat,
        navigateToTodayStressScore = navigateToTodayStressScore,
        popBackStack = popBackStack,
        navigateToMyPageChatHistory = navigateToMyPageChatHistory,
        navController = navController
    )
}

@Composable
fun DiaryAIFeedbackRecordScreen(
    padding: PaddingValues,
    date: String,
    diaryFeedbackState: DiaryFeedbackModel,
    totalDiary: TotalDiaryModel,
    navigateToChat: (Int) -> Unit,
    navigateToTodayStressScore: (String) -> Unit,
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

    val parsedDate = remember(date) {
        LocalDate.parse(date)
    }

    val formattedDate = stringResource(
        id = R.string.diary_ai_feedback_date,
        parsedDate.year, parsedDate.monthNumber, parsedDate.dayOfMonth
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
                summary = diaryFeedbackState.summary,
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
                    keywords = diaryFeedbackState.positiveKeywords,
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
                    keywords = diaryFeedbackState.negativeKeywords,
                    feedBackBoxBackgroundColor = colors.Gray100,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TitleSection(
                title = stringResource(R.string.diary_ai_feedback_recommendation_title)
            )

            DiaryAIFeedbackRecommendationBox(
                diaryAIFeedbackRecommendation = diaryFeedbackState.stressReliefSuggestions,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            val isDiaryRoute = previousRoute?.contains("Diary") == true

            StrHatButton(
                isDisabled = false,
                text =
                if (isDiaryRoute)
                    stringResource(R.string.confirm)
                else
                    stringResource(R.string.diary_ai_feedback_quit_button),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .weight(1f),
                onClick = popBackStack
            )
            StrHatButton(
                isDisabled = false,
                text =
                if (previousRoute?.contains("Diary") == true)
                    stringResource(R.string.my_page_ai_feedback_chat_history_button)
                else
                    stringResource(R.string.diary_ai_feedback_chat_button),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .weight(1f),
                onClick = {
                    if (previousRoute?.contains("Diary") == true)
                        navigateToMyPageChatHistory()
                    else
                        isChatModeBottomSheetVisible = true
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
            navigateToChat = {
                navigateToChat(totalDiary.diaryId)
            }
        )
    }
}