package com.konkuk.strhat.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.ui.theme.StrHatTheme
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun StrHatSelectableButtons(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectedOption: String? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        options.forEach { option ->
            StrHatSelectableButton(
                option = option,
                onClick = { onOptionSelected(option) },
                isSelected = option == selectedOption
            )
        }
    }
}

@Composable
fun StrHatSelectableButton(
    option: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = if (isSelected) colors.SubBlue else colors.Gray100,
                shape = RoundedCornerShape(6.dp)
            )
            .noRippleClickable(onClick = onClick)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = option,
            modifier = Modifier.padding(vertical = 14.dp),
            color = if (isSelected) colors.MainBlue else colors.Gray500,
            style = typography.body1_r_16
        )
    }
}

@Preview
@Composable
private fun PreviewStrHatSelectableButton() {
    val options = listOf(
        "남자", "여자"
    )
    var selectedOption by remember { mutableStateOf("") }
    StrHatTheme {
        StrHatSelectableButtons(
            options = options,
            onOptionSelected = { option -> selectedOption = option },
            selectedOption = selectedOption
        )
    }
}