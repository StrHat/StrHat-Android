package com.konkuk.strhat.core.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun LongTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    maxLength: Int,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (it.length <= maxLength) onValueChange(it)
            },
            placeholder = {
                Text(
                    text = hint,
                    style = typography.body2_r_15,
                    color = colors.Gray400
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.39f)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colors.MainBlack,
                unfocusedContainerColor = colors.Gray100,
                focusedContainerColor = colors.Gray100
            ),
            textStyle = typography.body1_r_16.copy(
                color = colors.MainBlack
            ),
            shape = RoundedCornerShape(4.dp)
        )
        Text(
            text = stringResource(R.string.textfield_long_length, value.length, maxLength),
            color = colors.Gray400,
            modifier = modifier.padding(top = 10.dp)
        )
    }

}

@Preview
@Composable
private fun PreviewLongTextFieldHobby() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        LongTextField(
            value = text,
            onValueChange = { text = it },
            hint = stringResource(R.string.textfield_hobby),
            maxLength = 1000,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewLongTextFieldStress() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        LongTextField(
            value = text,
            onValueChange = { text = it },
            hint = stringResource(R.string.textfield_stress_management),
            maxLength = 1000,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewLongTextFieldPersonality() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        LongTextField(
            value = text,
            onValueChange = { text = it },
            hint = stringResource(R.string.textfield_personality_type),
            maxLength = 1000,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewLongTextFieldDiary() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        LongTextField(
            value = text,
            onValueChange = { text = it },
            hint = stringResource(R.string.textfield_diary),
            maxLength = 1500,
            modifier = Modifier.fillMaxWidth()
        )
    }
}