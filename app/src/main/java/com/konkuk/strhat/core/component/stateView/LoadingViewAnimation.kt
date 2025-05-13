package com.konkuk.strhat.core.component.stateView

import androidx.annotation.StringRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.coroutines.delay

@Composable
fun LoadingViewAnimation(
    @StringRes loadingDescription: Int
) {
    val activeStep = remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            repeat(3) {
                activeStep.intValue = it
                delay(300L)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainWhite),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                val isActive = activeStep.intValue == index
                val offsetY by animateFloatAsState(
                    targetValue = if (isActive) -10f else 0f,
                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                    label = "DotOffset$index"
                )

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .offset(y = offsetY.dp)
                        .background(colors.MainBlue, CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(loadingDescription),
            color = colors.Gray500,
            style = typography.body3_m_14,
            textAlign = TextAlign.Center
        )
    }
}
