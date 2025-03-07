package com.konkuk.strhat.core.component.bottomsheet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.core.component.picker.Picker
import com.konkuk.strhat.core.component.picker.PickerState
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import java.util.Calendar

@Composable
fun YearSelectableButton(
    selectedYear: Int,
    onYearSelected: (Int) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.year_picker_title),
            color = colors.MainBlack,
            style = typography.title1_b_18
        )

        SelectableButton(
            selectedYear = selectedYear,
            currentYear = currentYear,
            onClick = { showBottomSheet = true }
        )

        if (showBottomSheet) {
            YearSelectionBottomSheet(
                selectedYear = selectedYear,
                currentYear = currentYear,
                onDismiss = { showBottomSheet = false },
                onYearSelected = {
                    onYearSelected(it)
                    showBottomSheet = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun YearSelectionBottomSheet(
    selectedYear: Int,
    currentYear: Int,
    onDismiss: () -> Unit,
    onYearSelected: (Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val years = remember { (2000..currentYear).toList() }
    val defaultYearIndex = years.indexOf(
        if (selectedYear == 0) currentYear else selectedYear
    ).takeIf { it >= 0 } ?: 0

    val pickerState = remember {
        PickerState().apply {
            selectedItem = years[defaultYearIndex].toString()
        }
    }

    ModalBottomSheet(
        modifier = Modifier,
        containerColor = colors.MainWhite,
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 28.dp)
        ) {
            Text(
                text = stringResource(R.string.year_picker_bottom_sheet_title),
                style = typography.title1_b_18,
                color = colors.MainBlack
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 20.dp)
                    .padding(horizontal = 33.dp)
            ) {
                Picker(
                    state = pickerState,
                    items = years.map { it.toString() },
                    initialSelectedIndex = defaultYearIndex,
                    textModifier = Modifier.padding(vertical = 16.dp)
                )
            }

            StrHatButton(
                text = stringResource(R.string.year_picker_bottom_sheet_button),
                isDisabled = false,
                onClick = {
                    val selectedYearValue = pickerState.selectedItem.toIntOrNull() ?: currentYear
                    onYearSelected(selectedYearValue)
                }
            )
        }
    }
}

@Composable
private fun SelectableButton(
    selectedYear: Int,
    currentYear: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable { onClick() }
            .border(
                width = 1.dp,
                color = colors.Gray400,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedYear == 0) "${currentYear}년" else "${selectedYear}년",
                color = if (selectedYear == 0) colors.Gray400 else colors.MainBlack,
                style = typography.body1_m_16
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_dropdown_down),
                contentDescription = null,
                tint = colors.MainBlack
            )
        }
    }
}

@Preview
@Composable
private fun PreviewYearSelectableButton() {
    YearSelectableButton(
        selectedYear = 0,
        onYearSelected = {}
    )
}
