package com.konkuk.strhat.core.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.konkuk.strhat.R
import com.konkuk.strhat.core.component.button.StrHatButton
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun StrHatDialog(
    titleResId: Int,
    imageResId: Int? = null,
    imageRatio: Float? = null,
    descriptionResId: Int? = null,
    diary: String? = null,
    onConfirmButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(color = colors.MainWhite, shape = RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_logo_blue),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(40.dp)
            )

            Text(
                text = stringResource(titleResId),
                style = typography.title1_b_18,
                color = colors.MainBlack
            )
        }

        if (imageResId != null && imageRatio != null && descriptionResId != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(imageResId),
                    contentDescription = null,
                    modifier = modifier
                        .padding(bottom = 20.dp)
                        .aspectRatio(imageRatio)
                )

                Text(
                    text = stringResource(descriptionResId),
                    style = typography.body3_m_14,
                    color = colors.Gray600
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                StrHatButton(
                    isDisabled = true,
                    text = stringResource(R.string.dismiss),
                    modifier = Modifier.weight(1f),
                    onClick = onDismissButtonClick
                )

                StrHatButton(
                    isDisabled = false,
                    text = stringResource(R.string.confirm),
                    modifier = Modifier.weight(1f),
                    onClick = onConfirmButtonClick
                )
            }
        } else if (diary != null) {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(
                        color = colors.Gray100,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .height(LocalConfiguration.current.screenHeightDp.dp * 0.37f)
                    .padding(15.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = diary,
                    style = typography.body2_r_15,
                    color = colors.MainBlack
                )
            }

            StrHatButton(
                isDisabled = false,
                text = stringResource(R.string.confirm),
                onClick = onConfirmButtonClick
            )
        }

    }
}

@Preview
@Composable
private fun PreviewLogoutDialog() {
    Box(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        Dialog(
            onDismissRequest = {}
        ) {
            StrHatDialog(
                titleResId = R.string.dialog_logout_title,
                imageResId = R.drawable.ic_strhat_dialog_all,
                imageRatio = 2f / 1f,
                descriptionResId = R.string.dialog_logout_description,
                onConfirmButtonClick = {},
                onDismissButtonClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewChatDialog() {
    Box(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        Dialog(
            onDismissRequest = {}
        ) {
            StrHatDialog(
                titleResId = R.string.dialog_chat_title,
                imageResId = R.drawable.ic_strhat_dialog_yellow_red_green,
                imageRatio = 1f / 1f,
                descriptionResId = R.string.dialog_chat_description,
                onConfirmButtonClick = {},
                onDismissButtonClick = {},
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDiaryDialog() {
    Box(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        Dialog(
            onDismissRequest = {}
        ) {
            StrHatDialog(
                titleResId = R.string.dialog_diary_title,
                diary = stringResource(R.string.textfield_diary),
                onConfirmButtonClick = {},
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }
    }
}