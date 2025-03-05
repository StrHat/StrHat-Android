package com.konkuk.strhat.core.component.progressbar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme.colors

@Composable
fun AnimatedProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = colors.Gray200
    val progressColor = colors.MainBlue

    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = stringResource(R.string.animated_progress_bar_label)
    )
    Box(
        modifier = modifier
            .background(
                color = colors.MainWhite
            )
            .fillMaxWidth()
            .height(8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = animatedProgress)
                .fillMaxHeight()
                .background(
                    color = progressColor,
                    shape = RoundedCornerShape(8.dp)
                )
        )
    }
}