package com.konkuk.strhat.core.component.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.strhat.R
import com.konkuk.strhat.core.util.color.setDropDownMenuTextStyle
import com.konkuk.strhat.core.util.modifier.noRippleClickable
import com.konkuk.strhat.domain.type.JobType
import com.konkuk.strhat.ui.theme.StrHatTheme.colors
import com.konkuk.strhat.ui.theme.StrHatTheme.typography

@Composable
fun JobDropDown(
    onJobSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedJob by remember { mutableStateOf(JobType.STUDENT.type) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable { isDropDownMenuExpanded = !isDropDownMenuExpanded }
                .border(
                    width = 1.dp,
                    color = if (isDropDownMenuExpanded) colors.MainBlue else colors.Gray400,
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
                    text = selectedJob,
                    color = colors.MainBlack,
                    style = typography.body1_m_16
                )
                if (isDropDownMenuExpanded) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_dropdown_up),
                        contentDescription = null,
                        tint = colors.MainBlack
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_dropdown_down),
                        contentDescription = null,
                        tint = colors.MainBlack
                    )
                }
            }
        }

        DropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false },
            modifier = modifier
                .background(color = colors.MainWhite)
                .fillMaxWidth()
        ) {
            DropdownMenuItem(
                text = {
                    val style = setDropDownMenuTextStyle(selectedJob, JobType.STUDENT.type)
                    Text(
                        text = JobType.STUDENT.type,
                        color = style.color,
                        style = style.typography
                    )
                },
                onClick = {
                    selectedJob = JobType.STUDENT.type
                    onJobSelected(JobType.STUDENT.type)
                    isDropDownMenuExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    val style = setDropDownMenuTextStyle(selectedJob, JobType.JOBSEEKER.type)
                    Text(
                        text = JobType.JOBSEEKER.type,
                        color = style.color,
                        style = style.typography
                    )
                },
                onClick = {
                    selectedJob = JobType.JOBSEEKER.type
                    onJobSelected(JobType.JOBSEEKER.type)
                    isDropDownMenuExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    val style = setDropDownMenuTextStyle(selectedJob, JobType.WORKER.type)
                    Text(
                        text = JobType.WORKER.type,
                        color = style.color,
                        style = style.typography
                    )
                },
                onClick = {
                    selectedJob = JobType.WORKER.type
                    onJobSelected(JobType.WORKER.type)
                    isDropDownMenuExpanded = false
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewJobDropDown() {
    Column(
        modifier = Modifier
            .background(color = colors.MainWhite)
            .fillMaxSize()
    ) {
        JobDropDown(onJobSelected = {})
    }

}