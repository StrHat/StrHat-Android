package com.konkuk.strhat.core.component.picker

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun Picker(
    items: List<String>,
    modifier: Modifier = Modifier,
    state: PickerState = rememberPickerState(),
    initialSelectedIndex: Int = 0,
    visibleItemsCount: Int = 3,
    textModifier: Modifier = Modifier
) {
    val visibleItemsMiddle = visibleItemsCount / 2
    val paddedItems =
        remember(items) { List(visibleItemsMiddle) { "" } + items + List(visibleItemsMiddle) { "" } }
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    var itemHeightPixels by remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels)
    val centerIndex by remember { derivedStateOf { listState.firstVisibleItemIndex + visibleItemsMiddle } }

    LaunchedEffect(initialSelectedIndex) {
        listState.scrollToItem(initialSelectedIndex + visibleItemsMiddle)
        state.selectedItem = items[initialSelectedIndex]
    }

    LaunchedEffect(listState) {
        snapshotFlow { centerIndex - visibleItemsMiddle }
            .mapNotNull { index -> items.getOrNull(index) }
            .distinctUntilChanged()
            .collect { state.selectedItem = it }
    }

    Box(modifier = modifier) {
        GuideLines(
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(itemHeightDp * visibleItemsCount)
                .fillMaxWidth()
        ) {
            items(paddedItems.size) { index ->
                PickerItem(
                    text = paddedItems[index],
                    isSelected = index == centerIndex,
                    onSizeChanged = { itemHeightPixels = it.height },
                    modifier = textModifier
                )
            }
        }

        Text(
            text = stringResource(R.string.year_picker_bottom_sheet),
            color = colors.MainBlack,
            style = typography.head2_r_20,
            modifier = modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}

@Composable
private fun PickerItem(
    text: String,
    isSelected: Boolean,
    onSizeChanged: (IntSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = 1,
        color = if (isSelected) colors.MainBlack else colors.Gray500,
        style = if (isSelected) typography.head2_b_20 else typography.head2_r_20,
        modifier = Modifier
            .onSizeChanged { size -> onSizeChanged(size) }
            .then(modifier)
            .semantics {
                if (isSelected) {
                    selected = true
                    stateDescription = "Selected"
                }
            }
    )
}

@Composable
private fun rememberPickerState() = remember { PickerState() }

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }