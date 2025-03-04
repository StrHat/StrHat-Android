package com.konkuk.strhat.core.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.core.component.bottomsheet.draghandle.BottomSheetDragHandle
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.picker.DatePicker
import com.konkuk.strhat.core.util.time.currentDateTime
import com.konkuk.strhat.core.util.time.toFormattedDateString
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBottomSheet(
    isVisible: Boolean,
    selectedDateString: String?,
    selectedDate: LocalDateTime?,
    onDismiss: () -> Unit,
    onDateSelected: (LocalDateTime?) -> Unit,
    modifier: Modifier = Modifier
) {
    var tempSelectedDate by remember { mutableStateOf(selectedDate) }
    var tempSelectedDateString by remember { mutableStateOf(selectedDateString) }

    if (isVisible) {
        Box(modifier = modifier.wrapContentHeight()) {
            ModalBottomSheet(
                onDismissRequest = { onDismiss() },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = colors.MainWhite,
                modifier = Modifier.fillMaxWidth(),
                dragHandle = {
                    BottomSheetDragHandle()
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "날짜 선택",
                        style = typography.title1_b_18,
                        color = colors.MainBlack
                    )

                    DatePicker(
                        modifier = Modifier.padding(vertical = 28.dp),
                        startDateTime = tempSelectedDate ?: currentDateTime,
                        onDateTimeSelected = { selectedDateTime ->
                            tempSelectedDate = selectedDateTime
                            tempSelectedDateString = selectedDateTime?.toFormattedDateString()
                        }
                    )

                    StrHatButton(
                        text = "확인",
                        onClick = {
                            onDateSelected(tempSelectedDate)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerBottomSheetPreview() {
    StrHatTheme {
        val (isVisible, setVisible) = remember { mutableStateOf(false) }
        val (selectedDate, setSelectedDate) = remember { mutableStateOf<LocalDateTime?>(null) }
        val (selectedDateString, setSelectedDateString) = remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.MainWhite)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { setVisible(true) }
            ) {
                Text("날짜 선택 버튼")
            }

            DatePickerBottomSheet(
                isVisible = isVisible,
                selectedDateString = selectedDateString,
                selectedDate = selectedDate,
                onDismiss = { setVisible(false) },
                onDateSelected = { newDate ->
                    setSelectedDate(newDate)
                    setSelectedDateString(newDate?.toFormattedDateString())
                }
            )
        }
    }
}