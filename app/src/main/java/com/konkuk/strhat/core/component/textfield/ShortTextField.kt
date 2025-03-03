package com.konkuk.strhat.core.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme

@Composable
fun ShortTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = StrHatTheme.typography.body1_r_16,
                color = StrHatTheme.colors.Gray400
            )
        },
        modifier = modifier
            .background(
                color = StrHatTheme.colors.Gray100,
                shape = RoundedCornerShape(4.dp)
            )
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = StrHatTheme.colors.MainBlack
        ),
        textStyle = StrHatTheme.typography.body1_r_16.copy(
            color = StrHatTheme.colors.MainBlack
        )
    )

}

@Preview
@Composable
private fun PreviewShortTextField() {
    var text by remember { mutableStateOf("") }
    ShortTextField(
        value = text,
        onValueChange = { text = it },
        hint = stringResource(R.string.textfield_nickname),
        modifier = Modifier.fillMaxWidth()
    )
}